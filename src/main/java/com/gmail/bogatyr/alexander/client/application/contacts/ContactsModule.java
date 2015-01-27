package com.gmail.bogatyr.alexander.client.application.contacts;

import com.gmail.bogatyr.alexander.client.application.contacts.display.ContactDisplayPresenter;
import com.gmail.bogatyr.alexander.client.application.contacts.display.ContactDisplayView;
import com.gmail.bogatyr.alexander.client.application.contacts.update.ContactEditPresenter;
import com.gmail.bogatyr.alexander.client.application.contacts.update.ContactEditView;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ContactsModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new RpcDispatchAsyncModule());
        bindPresenter(ContactsPresenter.class, ContactsPresenter.MyView.class, ContactsView.class,
                ContactsPresenter.MyProxy.class);
        bindPresenter(ContactDisplayPresenter.class, ContactDisplayPresenter.MyView.class, ContactDisplayView.class,
                ContactDisplayPresenter.MyProxy.class);
        bindPresenter(ContactEditPresenter.class, ContactEditPresenter.MyView.class, ContactEditView.class,
                ContactEditPresenter.MyProxy.class);
    }
}
