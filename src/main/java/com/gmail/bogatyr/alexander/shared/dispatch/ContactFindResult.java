package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.dispatch.rpc.shared.Result;

import java.util.List;

public class ContactFindResult implements Result {

    private List<ContactDto> contacts;

    protected ContactFindResult() {
        // Possibly for serialization.
    }

    public ContactFindResult(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactFindResult)) return false;

        ContactFindResult that = (ContactFindResult) o;

        if (contacts != null ? !contacts.equals(that.contacts) : that.contacts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return contacts != null ? contacts.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactFindResult{");
        sb.append("contacts=").append(contacts);
        sb.append('}');
        return sb.toString();
    }
}
