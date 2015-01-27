package com.gmail.bogatyr.alexander.server.dao;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.guice.PersistentModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

public class ContactDaoTest {
    
    private EntityManager em;
    private IContactDao contactDao;
    private Contact contact;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new JpaPersistModule("telephonebook-test-unit"), new PersistentModule());
        this.em = injector.getInstance(EntityManager.class);
        this.contactDao = injector.getInstance(ContactDao.class);
        this.contact = new Contact("+79788381497", "Random Person");
    }
    
    @Test
    public void testFind() {
        em.getTransaction().begin();
        contactDao.save(contact);
        em.getTransaction().commit();

        List<Contact> contacts = contactDao.find("+79788381497");
        assertTrue(isNotEmpty(contacts));

        contacts = contactDao.find("Random Person");
        assertTrue(isNotEmpty(contacts));

        contacts = contactDao.find("dom P");
        assertTrue(isNotEmpty(contacts));
    }
}
