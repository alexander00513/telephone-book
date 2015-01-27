package com.gmail.bogatyr.alexander.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Action;

public class ContactFindAllAction implements Action<ContactFindAllResult> {


  public ContactFindAllAction() {
    // Possibly for serialization.
  }

  @Override
  public String getServiceName() {
    return Action.DEFAULT_SERVICE_NAME + "ContactFindAll";
  }

  @Override
  public boolean isSecured() {
    return false;
  }
}
