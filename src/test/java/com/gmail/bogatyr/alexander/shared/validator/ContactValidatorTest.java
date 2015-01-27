package com.gmail.bogatyr.alexander.shared.validator;

import com.gmail.bogatyr.alexander.server.dao.ContactDao;
import com.gmail.bogatyr.alexander.server.dao.IContactDao;
import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.guice.PersistentModule;
import com.gmail.bogatyr.alexander.server.validator.ContactValidator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class ContactValidatorTest {

    private EntityManager em;
    private IContactDao contactDao;
    private ContactValidator contactValidator;
    private Contact contact;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(
                new JpaPersistModule("telephonebook-test-unit"),
                new PersistentModule()
        );
        this.em = injector.getInstance(EntityManager.class);
        this.contactDao = injector.getInstance(ContactDao.class);
        this.contactValidator = injector.getInstance(ContactValidator.class);
        this.contact = new Contact("+79788381496", "Random Person");
    }
    
    @Test
    public void testFind() {
        em.getTransaction().begin();
        contactDao.save(contact);
        em.getTransaction().commit();
        assertFalse(contactValidator.isUniqueNumber(contact));
        
        contact.setNumber("+79788381495");
        assertTrue(contactValidator.isUniqueNumber(contact));
    }
}
