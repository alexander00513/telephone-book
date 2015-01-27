package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.gwtplatform.dispatch.rpc.shared.Result;

public class ContactSaveResult implements Result { 

  private ContactDto contactDto;
  private String message;

  protected ContactSaveResult() {
    // Possibly for serialization.
  }

  public ContactSaveResult(ContactDto contactDto) {
    this.contactDto = contactDto;
  }

  public ContactSaveResult(String message) {
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
    if (!(o instanceof ContactSaveResult)) return false;

    ContactSaveResult result = (ContactSaveResult) o;

    if (contactDto != null ? !contactDto.equals(result.contactDto) : result.contactDto != null) return false;
    if (message != null ? !message.equals(result.message) : result.message != null) return false;

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
    final StringBuilder sb = new StringBuilder("ContactSaveResult{");
    sb.append("contactDto=").append(contactDto);
    sb.append(", message='").append(message).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
