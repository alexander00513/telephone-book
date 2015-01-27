package com.gmail.bogatyr.alexander.server.dispatch;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactUpdateAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactUpdateResult;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.dozer.Mapper;

public class ContactUpdateHandler implements ActionHandler<ContactUpdateAction, ContactUpdateResult> {

    @Inject
    private IContactManager contactManager;
    
    @Inject
    private Mapper mapper;

    @Override
    public ContactUpdateResult execute(ContactUpdateAction contactUpdateAction, ExecutionContext executionContext) throws ActionException {
        ContactUpdateResult result = null;
        if (contactUpdateAction != null) {
            ContactDto contactDto = contactUpdateAction.getContactDto();
            result = contactManager.update(mapper.map(contactDto, Contact.class));
        }
        return result;
    }

    @Override
    public Class<ContactUpdateAction> getActionType() {
        return ContactUpdateAction.class;
    }

    @Override
    public void undo(ContactUpdateAction contactUpdateAction, ContactUpdateResult contactUpdateResult, ExecutionContext executionContext) throws ActionException {

    }
}
