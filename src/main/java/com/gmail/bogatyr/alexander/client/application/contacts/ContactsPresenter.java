package com.gmail.bogatyr.alexander.client.application.contacts;

import com.gmail.bogatyr.alexander.client.place.NameTokens;
import com.gmail.bogatyr.alexander.client.place.ParameterTokens;
import com.gmail.bogatyr.alexander.shared.dispatch.*;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gmail.bogatyr.alexander.shared.i18n.AppMessages;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

import static com.gmail.bogatyr.alexander.client.place.ParameterTokens.TOKEN_SEARCH;

public class ContactsPresenter extends Presenter<ContactsPresenter.MyView, ContactsPresenter.MyProxy> implements ContactsUiHandlers {

    public interface MyView extends View, HasUiHandlers<ContactsUiHandlers> {
        void setCommonMsgLbl(String msg);
        Label getSearchErrLbl();
        Label getNumberErrLbl();
        Label getNameErrLbl();
        void hideMessages();
        void clearNumberFld();
        void clearNameFld();
        void setContactsToPanel(List<ContactDto> contacts);
        void setContactToPanel(ContactDto contactDto);
    }

    @ProxyStandard
    @NameToken(NameTokens.contacts)
    public interface MyProxy extends ProxyPlace<ContactsPresenter> {
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;
    private final AppMessages messages;
    
    private Map<String, Label> errLabels;
    
    private String query;

    @Inject
    ContactsPresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy,
                      PlaceManager placeManager,
                      DispatchAsync dispatcher, 
                      AppMessages messages) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.messages = messages;

        view.setUiHandlers(this);
        
        this.errLabels = new HashMap<>();
        errLabels.put("search", getView().getSearchErrLbl());
        errLabels.put("number", getView().getNumberErrLbl());
        errLabels.put("name", getView().getNameErrLbl());
    }

    @Override
    protected void onReset() {
        super.onReset();
        
        if (!isEmpty(query)) {
            searchContact(new ContactDto(query));
        } else {
            dispatcher.execute(new ContactFindAllAction(), new AsyncCallback<ContactFindAllResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    getView().setCommonMsgLbl(messages.commonServerError());
                }

                @Override
                public void onSuccess(ContactFindAllResult result) {
                    getView().setContactsToPanel(result.getContacts());
                }
            });
        }
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        this.query = request.getParameter(ParameterTokens.TOKEN_SEARCH, null);
    }

    @Override
    public void revealSearchContact(ContactDto contactDto) {
        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.contacts)
                .with(TOKEN_SEARCH, contactDto.getSearch())
                .build();
        placeManager.revealPlace(request);
        searchContact(contactDto);
    }

    private void searchContact(ContactDto contactDto) {
        getView().hideMessages();
        
        if (validate(contactDto, ContactDto.Search.class)) {
            dispatcher.execute(new ContactFindAction(contactDto.getSearch()), new AsyncCallback<ContactFindResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    getView().setCommonMsgLbl(messages.commonServerError());
                }

                @Override
                public void onSuccess(ContactFindResult result) {
                    getView().setContactsToPanel(result.getContacts());
                }
            });
        }
    }

    @Override
    public void saveContact(ContactDto contactDto) {
        getView().hideMessages();

        if (validate(contactDto, ContactDto.Save.class)) {
            getView().clearNumberFld();
            getView().clearNameFld();

            dispatcher.execute(new ContactSaveAction(contactDto), new AsyncCallback<ContactSaveResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    getView().setCommonMsgLbl(messages.commonServerError());
                }

                @Override
                public void onSuccess(ContactSaveResult result) {
                    String message = result.getMessage();
                    if (isEmpty(message)) {
                        onReset();
                    } else {
                        getView().setCommonMsgLbl(message);
                    }
                }
            });
        }
    }

    @Override
    public String buildViewToken(Long id) {
        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.contact_read)
                .with(ParameterTokens.TOKEN_ID, String.valueOf(id))
                .build();
        return placeManager.buildRelativeHistoryToken(request);
    }

    @Override
    public void deleteContact(ContactDto contactDto) {
        getView().hideMessages();

        dispatcher.execute(new ContactDeleteAction(contactDto), new AsyncCallback<ContactDeleteResult>() {
            @Override
            public void onFailure(Throwable caught) {
                getView().setCommonMsgLbl(messages.commonServerError());
            }

            @Override
            public void onSuccess(ContactDeleteResult result) {
                onReset();
            }
        });
    }
    
    private boolean validate(ContactDto contactDto, Class<?> type) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(contactDto, type);
        
        if(violations.size() > 0) {
            Iterator<ConstraintViolation<ContactDto>> iterator = violations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<?> violation = iterator.next();
                String path = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                resolveErrMsg(path, message);
            }    
            return false;
        }
        return true;
    }
    
    private void resolveErrMsg(String path, String message) {
        Label label = errLabels.get(path);
        String text = label.getText();
        if (!isEmpty(text)) {
            label.setText(text + " \n" + message);
        } else {
            label.setText(message);
        }
        label.setVisible(true);
    }

    private boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }
}
