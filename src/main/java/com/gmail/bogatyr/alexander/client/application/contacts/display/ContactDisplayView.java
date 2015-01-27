package com.gmail.bogatyr.alexander.client.application.contacts.display;

import com.gmail.bogatyr.alexander.client.place.NameTokens;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gmail.bogatyr.alexander.shared.i18n.AppMessages;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import javax.inject.Inject;

public class ContactDisplayView extends ViewWithUiHandlers<ContactDisplayHandlers> implements ContactDisplayPresenter.MyView {

    interface Binder extends UiBinder<Widget, ContactDisplayView> {
    }

    @UiField
    Label title;
    
    @UiField
    Label commonMsgLabel;

    @UiField
    Label idLabel;
    @UiField
    Label numberLabel;
    @UiField
    Label nameLabel;

    @UiField
    Button deleteButton;
    
    @UiField
    Button editButton;

    @UiField
    Button listButton;
    
    private final PlaceManager placeManager;
    private final AppMessages messages;

    @Inject
    public ContactDisplayView(Binder uiBinder, final PlaceManager placeManager, AppMessages messages) {
        this.placeManager = placeManager;
        this.messages = messages;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setCommonMsgLbl(String message) {
        this.commonMsgLabel.setText(message);
    }

    @Override
    public void hideCommonMsgLbl() {
        this.commonMsgLabel.setVisible(false);
        this.commonMsgLabel.setText(null);
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setContactToPanel(ContactDto contactDto) {
        this.idLabel.setText(String.valueOf(contactDto.getId()));
        this.numberLabel.setText(contactDto.getNumber());
        this.nameLabel.setText(contactDto.getName());
    }

    @UiHandler("deleteButton")
    public void deleteContact(ClickEvent event) {
        if(Window.confirm(messages.commonDeleteEntryConfirmationMessage())) {
            String stringId = idLabel.getText();
            Long id = Long.parseLong(stringId);
            String number = numberLabel.getText();
            String name = nameLabel.getText();
            getUiHandlers().deleteContact(new ContactDto(id, number, name));
        }
    }

    @UiHandler("editButton")
    public void editContact(ClickEvent event) {
        getUiHandlers().revealEditContact(idLabel.getText());
    }
    
    @UiHandler("listButton")
    public void listContact(ClickEvent event) {
        PlaceRequest request = new PlaceRequest.Builder()
                .nameToken(NameTokens.contacts)
                .build();
        placeManager.revealPlace(request);
    }
}