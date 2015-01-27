package com.gmail.bogatyr.alexander.server.dispatch;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactDeleteAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactDeleteResult;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.dozer.Mapper;

public class ContactDeleteHandler implements ActionHandler<ContactDeleteAction, ContactDeleteResult> {

    @Inject
    private IContactManager contactManager;
    
    @Inject
    private Mapper mapper;

    @Override
    public ContactDeleteResult execute(ContactDeleteAction contactDeleteAction, ExecutionContext executionContext) throws ActionException {
        ContactDto contactDto = contactDeleteAction.getContactDto();
        if (contactDto != null) {
            Contact contact = mapper.map(contactDto, Contact.class);
            contactManager.delete(contact);
        }
        return new ContactDeleteResult();
    }

    @Override
    public Class<ContactDeleteAction> getActionType() {
        return ContactDeleteAction.class;
    }

    @Override
    public void undo(ContactDeleteAction contactDeleteAction, ContactDeleteResult contactDeleteResult, 
                     ExecutionContext executionContext) throws ActionException {
    }
}
