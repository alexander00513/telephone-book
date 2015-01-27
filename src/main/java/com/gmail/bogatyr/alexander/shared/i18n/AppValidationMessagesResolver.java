package com.gmail.bogatyr.alexander.shared.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.validation.client.AbstractValidationMessageResolver;
import com.google.gwt.validation.client.UserValidationMessagesResolver;

public class AppValidationMessagesResolver extends AbstractValidationMessageResolver
    implements UserValidationMessagesResolver {

  protected AppValidationMessagesResolver() {
    super((ConstantsWithLookup) GWT.create(AppMessages.class));
  }
}