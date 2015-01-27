package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.dispatch.rpc.shared.Action;

public class ContactDeleteAction implements Action<ContactDeleteResult> {

  private ContactDto contactDto;

  protected ContactDeleteAction() {
    // Possibly for serialization.
  }

  public ContactDeleteAction(ContactDto contactDto) {
    this.contactDto = contactDto;
  }

  @Override
  public String getServiceName() {
    return Action.DEFAULT_SERVICE_NAME + "ContactDelete";
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
    if (!(o instanceof ContactDeleteAction)) return false;

    ContactDeleteAction that = (ContactDeleteAction) o;

    if (contactDto != null ? !contactDto.equals(that.contactDto) : that.contactDto != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return contactDto != null ? contactDto.hashCode() : 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ContactDeleteAction{");
    sb.append("contactDto=").append(contactDto);
    sb.append('}');
    return sb.toString();
  }
}
