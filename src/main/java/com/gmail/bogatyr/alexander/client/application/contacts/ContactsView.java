package com.gmail.bogatyr.alexander.client.application.contacts;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gmail.bogatyr.alexander.shared.i18n.AppMessages;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import javax.inject.Inject;
import java.util.List;

public class ContactsView extends ViewWithUiHandlers<ContactsUiHandlers> implements ContactsPresenter.MyView {

    interface Binder extends UiBinder<Widget, ContactsView> {
    }

    @UiField
    Label commonMsgLabel;
    
    @UiField
    TextBox searchField;
    @UiField
    Label searchErrorLabel;
    @UiField
    Button searchButton;

    @UiField
    TextBox numberField;
    @UiField
    Label numberErrorLabel;

    @UiField
    TextBox nameField;
    @UiField
    Label nameErrorLabel;
    
    @UiField
    Button addButton;
    @UiField
    FlowPanel contactPanel;

    
    private final PlaceManager placeManager;
    private final AppMessages messages;
    
    @Inject
    public ContactsView(Binder uiBinder, PlaceManager placeManager, AppMessages messages) {
        this.placeManager = placeManager;
        this.messages = messages;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setCommonMsgLbl(String message) {
        this.commonMsgLabel.setVisible(true);
        this.commonMsgLabel.setText(message);
    }
    
    @Override
    public Label getSearchErrLbl() {
        return searchErrorLabel;
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
        this.searchErrorLabel.setVisible(false);
        this.searchErrorLabel.setText(null);
        this.numberErrorLabel.setVisible(false);
        this.numberErrorLabel.setText(null);
        this.nameErrorLabel.setVisible(false);
        this.nameErrorLabel.setText(null);
    }

    @Override
    public void clearNumberFld() {
        this.numberField.setText(null);
    }

    @Override
    public void clearNameFld() {
        this.nameField.setText(null);
    }

    @Override
    public void setContactsToPanel(List<ContactDto> contacts) {
        this.contactPanel.clear();
        for (ContactDto contactDto : contacts) {
            appendContactToPanel(contactDto);
        }
    }

    @Override
    public void setContactToPanel(ContactDto contactDto) {
        appendContactToPanel(contactDto);
    }

    public void appendContactToPanel(final ContactDto contactDto) {
        contactPanel.add(new Hyperlink(contactDto.getNumber(), getUiHandlers().buildViewToken(contactDto.getId())));
        contactPanel.add(new Label(contactDto.getName()));
        
        Button delete = new Button(messages.commonButtonDelete());
        delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if(Window.confirm(messages.commonDeleteEntryConfirmationMessage())) {
                    getUiHandlers().deleteContact(contactDto);
                }
            }
        });
        contactPanel.add(delete);
    }
    
    @UiHandler("searchButton")
    public void findContact(ClickEvent event) {
        getUiHandlers().revealSearchContact(new ContactDto(searchField.getText()));
    }
    
    @UiHandler("addButton")
    public void saveContact(ClickEvent event) {
        String number = numberField.getText();
        String name = nameField.getText();
        getUiHandlers().saveContact(new ContactDto(number, name));
    }
}