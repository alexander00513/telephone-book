package com.gmail.bogatyr.alexander.server.dispatch;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindResult;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import java.util.List;

import static com.gmail.bogatyr.alexander.shared.helper.AppHelper.convertToDto;

public class ContactFindHandler implements ActionHandler<ContactFindAction, ContactFindResult> {

    @Inject
    private IContactManager contactManager;

    @Override
    public ContactFindResult execute(ContactFindAction contactFindAction, ExecutionContext executionContext) throws ActionException {
        List<Contact> contacts = null;
        if (contactFindAction != null) {
            String query = contactFindAction.getQuery();
            contacts = contactManager.find(query);
        }
        List<ContactDto> contactDtos = convertToDto(contacts);
        return new ContactFindResult(contactDtos);
    }

    @Override
    public Class<ContactFindAction> getActionType() {
        return ContactFindAction.class;
    }

    @Override
    public void undo(ContactFindAction contactFindAction, ContactFindResult contactFindResult, 
                     ExecutionContext executionContext) throws ActionException {
    }
}
