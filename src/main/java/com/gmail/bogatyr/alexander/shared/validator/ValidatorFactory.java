package com.gmail.bogatyr.alexander.shared.validator;

import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import javax.validation.Validator;

public final class ValidatorFactory extends AbstractGwtValidatorFactory {

    @GwtValidation(value = ContactDto.class, groups = {ContactDto.Search.class, ContactDto.Save.class})
    public interface GwtValidator extends Validator {
    }
    
    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }
}
