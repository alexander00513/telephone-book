package com.gmail.bogatyr.alexander.client.application.contacts.display;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.mvp.client.UiHandlers;

public interface ContactDisplayHandlers extends UiHandlers {

    void deleteContact(ContactDto contactDto);
    void revealEditContact(String id);
    void revealListContact();
}
