package com.gmail.bogatyr.alexander.client.application.contacts.update;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.mvp.client.UiHandlers;

public interface ContactEditUiHandlers extends UiHandlers {

    void updateContact(ContactDto contactDto);
    void revealListContact();
}
