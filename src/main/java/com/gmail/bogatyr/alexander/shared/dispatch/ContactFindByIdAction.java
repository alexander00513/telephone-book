package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Action;

import java.lang.Long;

public class ContactFindByIdAction implements Action<ContactFindByIdResult> { 

  private Long id;

  protected ContactFindByIdAction() {
    // Possibly for serialization.
  }

  public ContactFindByIdAction(Long id) {
    this.id = id;
  }

  @Override
  public String getServiceName() {
    return Action.DEFAULT_SERVICE_NAME + "ContactFindById";
  }

  @Override
  public boolean isSecured() {
    return false;
  }

  public Long getId(){
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ContactFindByIdAction)) return false;

    ContactFindByIdAction that = (ContactFindByIdAction) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ContactFindByIdAction{");
    sb.append("id=").append(id);
    sb.append('}');
    return sb.toString();
  }
}
