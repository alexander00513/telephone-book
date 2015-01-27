package com.gmail.bogatyr.alexander.client.gin;

import com.gmail.bogatyr.alexander.client.application.contacts.ContactsModule;
import com.gmail.bogatyr.alexander.client.place.NameTokens;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new ContactsModule());

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.contacts);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.contacts);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.contacts);
    }
}
