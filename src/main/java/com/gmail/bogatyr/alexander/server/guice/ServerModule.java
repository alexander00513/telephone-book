package com.gmail.bogatyr.alexander.server.guice;

import com.gmail.bogatyr.alexander.server.dispatch.*;
import com.gmail.bogatyr.alexander.shared.dispatch.*;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(ContactFindAction.class, ContactFindHandler.class);
        bindHandler(ContactFindAllAction.class, ContactFindAllHandler.class);
        bindHandler(ContactSaveAction.class, ContactSaveHandler.class);
        bindHandler(ContactFindByIdAction.class, ContactFindByIdHandler.class);
        bindHandler(ContactUpdateAction.class, ContactUpdateHandler.class);
        bindHandler(ContactDeleteAction.class, ContactDeleteHandler.class);
    }
}
