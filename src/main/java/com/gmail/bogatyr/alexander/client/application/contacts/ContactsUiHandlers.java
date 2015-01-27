package com.gmail.bogatyr.alexander.client.application.contacts;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.mvp.client.UiHandlers;

public interface ContactsUiHandlers extends UiHandlers {

    void revealSearchContact(ContactDto contactDto);
    void saveContact(ContactDto contactDto);
    String buildViewToken(Long id);
    void deleteContact(ContactDto contactDto);
}
