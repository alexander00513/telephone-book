package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.dispatch.rpc.shared.Action;

public class ContactUpdateAction implements Action<ContactUpdateResult> { 

  private ContactDto contactDto;

  protected ContactUpdateAction() {
    // Possibly for serialization.
  }

  public ContactUpdateAction(ContactDto contactDto) {
    this.contactDto = contactDto;
  }

  @Override
  public String getServiceName() {
    return Action.DEFAULT_SERVICE_NAME + "ContactUpdate";
  }

  @Override
  public boolean isSecured() {
    return false;
  }

  public ContactDto getContactDto() {
    return contactDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ContactUpdateAction)) return false;

    ContactUpdateAction that = (ContactUpdateAction) o;

    if (contactDto != null ? !contactDto.equals(that.contactDto) : that.contactDto != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return contactDto != null ? contactDto.hashCode() : 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ContactUpdateAction{");
    sb.append("contactDto=").append(contactDto);
    sb.append('}');
    return sb.toString();
  }
}
