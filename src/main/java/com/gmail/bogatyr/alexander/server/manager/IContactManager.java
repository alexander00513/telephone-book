package com.gmail.bogatyr.alexander.server.manager;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindByIdResult;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactSaveResult;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactUpdateResult;

import java.io.Serializable;
import java.util.List;

public interface IContactManager extends Serializable {

    List<Contact> find(String query);
    List<Contact> findAll();
    ContactSaveResult save(Contact contact);
    ContactFindByIdResult findById(Long id);
    ContactUpdateResult update(Contact contact);
    void delete(Contact contact);
}
