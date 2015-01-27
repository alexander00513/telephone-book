package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.dispatch.rpc.shared.Result;

public class ContactUpdateResult implements Result { 

  private ContactDto contactDto;
  private String message;

  protected ContactUpdateResult() {
    // Possibly for serialization.
  }

  public ContactUpdateResult(ContactDto contactDto) {
    this.contactDto = contactDto;
  }

  public ContactUpdateResult(String message) {
    this.message = message;
  }

  public ContactDto getContactDto() {
    return contactDto;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ContactUpdateResult)) return false;

    ContactUpdateResult that = (ContactUpdateResult) o;

    if (contactDto != null ? !contactDto.equals(that.contactDto) : that.contactDto != null) return false;
    if (message != null ? !message.equals(that.message) : that.message != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = contactDto != null ? contactDto.hashCode() : 0;
    result = 31 * result + (message != null ? message.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ContactUpdateResult{");
    sb.append("contactDto=").append(contactDto);
    sb.append(", message='").append(message).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
