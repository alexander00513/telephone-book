package com.gmail.bogatyr.alexander.client.application.contacts.update;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;

public class ContactEditView extends ViewWithUiHandlers<ContactEditUiHandlers> implements ContactEditPresenter.MyView {

    interface Binder extends UiBinder<Widget, ContactEditView> {
    }

    @UiField
    Label title;

    @UiField
    Label commonMsgLabel;

    @UiField
    Label idLabel;
    @UiField
    TextBox numberField;
    @UiField
    Label numberErrorLabel;

    @UiField
    TextBox nameField;
    @UiField
    Label nameErrorLabel;
    
    @UiField
    Button saveButton;

    @UiField
    Button cancelButton;

    
    @Inject
    public ContactEditView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }
    
    @Override
    public void setCommonMsgLbl(String message) {
        this.commonMsgLabel.setText(message);
        this.commonMsgLabel.setVisible(true);
    }

    @Override
    public Label getNumberErrLbl() {
        return numberErrorLabel;
    }

    @Override
    public Label getNameErrLbl() {
        return nameErrorLabel;
    }

    @Override
    public void hideMessages() {
        this.commonMsgLabel.setVisible(false);
        this.commonMsgLabel.setText(null);
        this.numberErrorLabel.setVisible(false);
        this.numberErrorLabel.setText(null);
        this.nameErrorLabel.setVisible(false);
        this.nameErrorLabel.setText(null);
    }

    @Override
    public void setContactToPanel(ContactDto contactDto) {
        this.idLabel.setText(String.valueOf(contactDto.getId()));
        this.numberField.setText(contactDto.getNumber());
        this.nameField.setText(contactDto.getName());
    }
    
    @UiHandler("saveButton")
    public void saveContact(ClickEvent event) {
        String stringId = idLabel.getText();
        Long id = Long.parseLong(stringId);
        String number = numberField.getText();
        String name = nameField.getText();
        getUiHandlers().updateContact(new ContactDto(id, number, name));
    }

    @UiHandler("cancelButton")
    public void cancelContact(ClickEvent event) {
        getUiHandlers().revealListContact();
    }
}