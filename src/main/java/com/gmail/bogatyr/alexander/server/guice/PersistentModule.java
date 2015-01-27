package com.gmail.bogatyr.alexander.server.guice;

import com.gmail.bogatyr.alexander.server.dao.ContactDao;
import com.gmail.bogatyr.alexander.server.dao.IContactDao;
import com.gmail.bogatyr.alexander.server.manager.ContactManager;
import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.server.validator.ContactValidator;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.hibernate.Session;

import javax.persistence.EntityManager;

public class PersistentModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PersistentInitializer.class).asEagerSingleton();
        bind(ContactValidator.class);
        bind(IContactDao.class).to(ContactDao.class);
        bind(IContactManager.class).to(ContactManager.class);
    }
    
    @Provides
    public Session provideSession(EntityManager em) {
        return em.unwrap(Session.class);
    }
}
