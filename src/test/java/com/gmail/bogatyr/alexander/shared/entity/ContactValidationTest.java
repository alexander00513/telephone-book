package com.gmail.bogatyr.alexander.shared.entity;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ContactValidationTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
    
    @Test
    public void testSetNumber() {
        //valid case
        Contact contact = new Contact("+79788381497", "Random Person");
        assertEquals(0, validate(contact));

        contact.setNumber("+7978");
        assertEquals(0, validate(contact));

        contact.setNumber("+79788381");
        assertEquals(0, validate(contact));
        
        //null
        contact.setNumber(null);
        assertEquals(1, validate(contact));
        
        //min
        contact.setNumber("+797");
        assertEquals(1, validate(contact));
        
        //max
        contact.setNumber("+797883814971");
        assertEquals(1, validate(contact));
    }

    @Test
    public void testSetName() {
        //valid case
        Contact contact = new Contact("+79788381497", "Random Person");
        assertEquals(0, validate(contact));

        contact.setName("Ran");
        assertEquals(0, validate(contact));

        contact.setName("Random");
        assertEquals(0, validate(contact));
        
        //null
        contact.setName(null);
        assertEquals(1, validate(contact));
        
        //min
        contact.setName("Ra");
        assertEquals(1, validate(contact));
        
        //max
        StringBuilder builder = new StringBuilder(70);
        for (int i = 0; i < 7; i++) {
            builder.append("abcdefghij");
        }
        contact.setName(builder.toString());
        assertEquals(1, validate(contact));
    }

    /**
     * Validate contact object
     * @return error messages count
     */
    private static int validate(Contact contact) {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        return violations.size();
    }
}
