package com.gmail.bogatyr.alexander.server.dispatch;

import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindByIdAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindByIdResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ContactFindByIdHandler implements ActionHandler<ContactFindByIdAction, ContactFindByIdResult> {

    @Inject
    private IContactManager contactManager;

    @Override
    public ContactFindByIdResult execute(ContactFindByIdAction contactFindByIdAction, ExecutionContext executionContext) throws ActionException {
        ContactFindByIdResult result = null;
        if (contactFindByIdAction != null) {
            Long id = contactFindByIdAction.getId();
            result = contactManager.findById(id);
        }
        return result;
    }

    @Override
    public Class<ContactFindByIdAction> getActionType() {
        return ContactFindByIdAction.class;
    }

    @Override
    public void undo(ContactFindByIdAction contactFindByIdAction, ContactFindByIdResult contactFindByIdResult, 
                     ExecutionContext executionContext) throws ActionException {
    }
}
