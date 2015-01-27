package com.gmail.bogatyr.alexander.server.validator;

import com.gmail.bogatyr.alexander.server.dao.IContactDao;
import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.google.inject.Inject;

public class ContactValidator {
    
    @Inject
    private IContactDao contactDao;
    
    public boolean isUniqueNumber(Contact contact) {
        if (contact != null) {
            String number = contact.getNumber();
            return !contactDao.isNumberExist(number);
        }
        return false;
    }
}
