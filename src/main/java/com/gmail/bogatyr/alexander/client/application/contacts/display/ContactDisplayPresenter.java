package com.gmail.bogatyr.alexander.client.application.contacts.display;

import com.gmail.bogatyr.alexander.client.place.NameTokens;
import com.gmail.bogatyr.alexander.client.place.ParameterTokens;
import com.gmail.bogatyr.alexander.shared.dispatch.*;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gmail.bogatyr.alexander.shared.i18n.AppMessages;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

public class ContactDisplayPresenter extends Presenter<ContactDisplayPresenter.MyView, ContactDisplayPresenter.MyProxy>
        implements ContactDisplayHandlers {

    public interface MyView extends View, HasUiHandlers<ContactDisplayHandlers> {
        void setCommonMsgLbl(String msg);
        void hideCommonMsgLbl();
        void setTitle(String title);
        void setContactToPanel(ContactDto contactDto);
    }

    @ProxyStandard
    @NameToken(NameTokens.contact_read)
    public interface MyProxy extends ProxyPlace<ContactDisplayPresenter> {
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;
    private final AppMessages messages;

    private Long id;
    

    @Inject
    ContactDisplayPresenter(EventBus eventBus,
                            MyView view,
                            MyProxy proxy,
                            PlaceManager placeManager,
                            DispatchAsync dispatcher, 
                            AppMessages messages) {
        super(eventBus, view, proxy, RevealType.Root);
        
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.messages = messages;
        getView().setUiHandlers(this);
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
                        getView().setTitle(messages.displayWidgetLabelTitle(contact.getName()));
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
    public void deleteContact(ContactDto contactDto) {
        getView().hideCommonMsgLbl();

        dispatcher.execute(new ContactDeleteAction(contactDto), new AsyncCallback<ContactDeleteResult>() {
            @Override
            public void onFailure(Throwable caught) {
                getView().setCommonMsgLbl(messages.commonServerError());
            }

            @Override
            public void onSuccess(ContactDeleteResult result) {
                revealListContact();
            }
        });
    }
    
    @Override
    public void revealEditContact(String id) {
        if (!isEmpty(id)) {
            PlaceRequest request = new PlaceRequest.Builder()
                    .nameToken(NameTokens.contact_edit)
                    .with(ParameterTokens.TOKEN_ID, id.trim())
                    .build();
            placeManager.revealPlace(request);
        } else {
            revealListContact();
        }
    }


    @Override
    public void revealListContact() {
        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.contacts)
                .build();
        placeManager.revealPlace(request);
    }

    private boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }
}
