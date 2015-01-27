package com.gmail.bogatyr.alexander.server.manager;

import com.gmail.bogatyr.alexander.server.dao.IContactDao;
import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.validator.ContactValidator;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindByIdResult;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactSaveResult;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactUpdateResult;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.gmail.bogatyr.alexander.shared.helper.AppHelper.convertToDto;

public class ContactManager implements IContactManager {

    @Inject
    private IContactDao contactDao;
    
    @Inject
    private ContactValidator contactValidator;
    
    @Inject
    private EntityManager em;
    
    @Override
    @Transactional
    public List<Contact> find(String query) {
        List<Contact> result;
        result = contactDao.find(query);
        return result;
    }

    @Override
    @Transactional
    public List<Contact> findAll() {
        List<Contact> result;
        result = contactDao.findAll();
        return result;
    }

    @Override
    @Transactional
    public ContactSaveResult save(Contact contact) {
        ContactSaveResult result;
        if (!contactValidator.isUniqueNumber(contact)) {
            result = new ContactSaveResult("contact can't be saved - number is exist");
        } else {
            Contact contactDb = contactDao.save(contact);
            result = new ContactSaveResult(convertToDto(contactDb));
        }
        return result;
    }

    @Override
    @Transactional
    public ContactFindByIdResult findById(Long id) {
        ContactFindByIdResult result;
        Contact contactDb = contactDao.findById(id);
        if(contactDb != null) {
            result = new ContactFindByIdResult(convertToDto(contactDb));
        } else {
            result = new ContactFindByIdResult("contact not found");
        }
        return result;
        
    }

    @Override
    @Transactional
    public ContactUpdateResult update(Contact contact) {
        ContactUpdateResult result;
        if (contactDao.contains(contact)) {
            Contact contactDb = contactDao.update(contact);
            result = new ContactUpdateResult(convertToDto(contactDb));
        } else {
            result = new ContactUpdateResult("contact not found");
        }
        return result;
        
    }
    
    @Override
    @Transactional
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }
}
