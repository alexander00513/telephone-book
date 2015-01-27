package com.gmail.bogatyr.alexander.client.application.contacts.update;

import com.gmail.bogatyr.alexander.client.place.NameTokens;
import com.gmail.bogatyr.alexander.client.place.ParameterTokens;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindByIdAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindByIdResult;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactUpdateAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactUpdateResult;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ContactEditPresenter extends Presenter<ContactEditPresenter.MyView, ContactEditPresenter.MyProxy>
        implements ContactEditUiHandlers {

    public interface MyView extends View, HasUiHandlers<ContactEditUiHandlers> {
        void setTitle(String title);
        void setCommonMsgLbl(String msg);
        Label getNumberErrLbl();
        Label getNameErrLbl();
        void hideMessages();
        void setContactToPanel(ContactDto contactDto);
    }

    @ProxyStandard
    @NameToken(NameTokens.contact_edit)
    public interface MyProxy extends ProxyPlace<ContactEditPresenter> {
        
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;
    private final AppMessages messages;

    private Map<String, Label> errLabels;
    
    private Long id;
    
    
    @Inject
    ContactEditPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         PlaceManager placeManager,
                         DispatchAsync dispatcher, AppMessages messages) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.messages = messages;
        getView().setUiHandlers(this);

        this.errLabels = new HashMap<>();
        errLabels.put("number", getView().getNumberErrLbl());
        errLabels.put("name", getView().getNameErrLbl());
    }

    @Override
    protected void onReset() {
        super.onReset();
        
        dispatcher.execute(new ContactFindByIdAction(id), new AsyncCallback<ContactFindByIdResult>() {
            @Override
            public void onFailure(Throwable caught) {
                getView().setCommonMsgLbl(messages.commonServerError());
            }

            @Override
            public void onSuccess(ContactFindByIdResult result) {
                String message = result.getMessage();
                if (isEmpty(message)) {
                    ContactDto contact = result.getContactDto();
                    if (contact != null) {
                        getView().setTitle(messages.editWidgetLabelTitle(contact.getName()));
                        getView().setContactToPanel(result.getContactDto());
                    }
                } else {
                    getView().setCommonMsgLbl(message);
                }
            }
        });
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);

        String stringId = request.getParameter(ParameterTokens.TOKEN_ID, null);
        try {
            id = Long.parseLong(stringId);
        } catch (NumberFormatException e) {
            id = 0L;
        }
    }

    @Override
    public void updateContact(final ContactDto contactDto) {
        getView().hideMessages();

        if (validate(contactDto, ContactDto.Save.class)) {
            dispatcher.execute(new ContactUpdateAction(contactDto), new AsyncCallback<ContactUpdateResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    getView().setCommonMsgLbl(messages.commonServerError());
                }

                @Override
                public void onSuccess(ContactUpdateResult result) {
                    String message = result.getMessage();
                    if (isEmpty(message)) {
                        ContactDto contact = result.getContactDto();
                        if (contact != null) {
                            revealReadContact(contact.getId());    
                        }
                    } else {
                        getView().setCommonMsgLbl(message);
                    }
                }
            });
        }
    }

    @Override
    public void revealListContact() {
        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.contacts)
                .build();
        placeManager.revealPlace(request);
    }

    public void revealReadContact(Long id) {
        if (id != null) {
            PlaceRequest request = new PlaceRequest.Builder()
                    .nameToken(NameTokens.contact_read)
                    .with(ParameterTokens.TOKEN_ID, String.valueOf(id))
                    .build();
            placeManager.revealPlace(request);
        } else {
            revealListContact();
        }
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
