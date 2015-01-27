package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Action;

public class ContactFindAction implements Action<ContactFindResult> {

    private String query;

    protected ContactFindAction() {
        // Possibly for serialization.
    }

    public ContactFindAction(String query) {
        this.query = query;
    }

    @Override
    public String getServiceName() {
        return Action.DEFAULT_SERVICE_NAME + "ContactFind";
    }

    @Override
    public boolean isSecured() {
        return false;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactFindAction)) return false;

        ContactFindAction that = (ContactFindAction) o;

        if (query != null ? !query.equals(that.query) : that.query != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return query != null ? query.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactFindAction{");
        sb.append("query='").append(query).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
