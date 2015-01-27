package com.gmail.bogatyr.alexander.server.dispatch;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactSaveAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactSaveResult;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.dozer.Mapper;

public class ContactSaveHandler implements ActionHandler<ContactSaveAction, ContactSaveResult> {

    @Inject
    private IContactManager contactManager;

    @Inject
    private Mapper mapper;

    @Override
    public ContactSaveResult execute(ContactSaveAction contactSaveAction, ExecutionContext executionContext) throws ActionException {
        ContactSaveResult result = null;
        if (contactSaveAction != null) {
            ContactDto contactDto = contactSaveAction.getContactDto();
            result = contactManager.save(mapper.map(contactDto, Contact.class));
        }
        return result;
    }

    @Override
    public Class<ContactSaveAction> getActionType() {
        return ContactSaveAction.class;
    }

    @Override
    public void undo(ContactSaveAction contactSaveAction, ContactSaveResult contactSaveResult, ExecutionContext executionContext) throws ActionException {

    }
}
