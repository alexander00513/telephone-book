package com.gmail.bogatyr.alexander.server.dispatch;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.server.manager.IContactManager;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindAllAction;
import com.gmail.bogatyr.alexander.shared.dispatch.ContactFindAllResult;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import java.util.List;

import static com.gmail.bogatyr.alexander.shared.helper.AppHelper.convertToDto;

public class ContactFindAllHandler implements ActionHandler<ContactFindAllAction, ContactFindAllResult> {

    @Inject
    private IContactManager contactManager;

    @Override
    public ContactFindAllResult execute(final ContactFindAllAction action, final ExecutionContext context) throws ActionException {
        List<Contact> contacts = contactManager.findAll();
        List<ContactDto> contactDtos = convertToDto(contacts);
        return new ContactFindAllResult(contactDtos);
    }

    @Override
    public Class<ContactFindAllAction> getActionType() {
        return ContactFindAllAction.class;
    }

    @Override
    public void undo(final ContactFindAllAction action, final ContactFindAllResult result,
            final ExecutionContext context) throws ActionException {
    }
}
