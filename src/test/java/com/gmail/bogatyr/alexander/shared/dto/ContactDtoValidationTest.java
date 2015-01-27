package com.gmail.bogatyr.alexander.shared.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ContactDtoValidationTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
    
    @Test
    public void testSetSearch() {
        //valid case
        ContactDto ContactDto = new ContactDto("Search query");
        assertEquals(0, validate(ContactDto, ContactDto.Search.class));

        ContactDto.setSearch("Sea");
        assertEquals(0, validate(ContactDto, ContactDto.Search.class));

        ContactDto.setSearch("Search");
        assertEquals(0, validate(ContactDto, ContactDto.Search.class));

        //null
        ContactDto.setSearch(null);
        assertEquals(1, validate(ContactDto, ContactDto.Search.class));

        //min
        ContactDto.setSearch("");
        assertEquals(1, validate(ContactDto, ContactDto.Search.class));

        //max
        StringBuilder builder = new StringBuilder(70);
        for (int i = 0; i < 7; i++) {
            builder.append("abcdefghij");
        }
        ContactDto.setSearch(builder.toString());
        assertEquals(1, validate(ContactDto, ContactDto.Search.class));
    }
    
    @Test
    public void testSetNumber() {
        //valid case
        ContactDto ContactDto = new ContactDto("+79788381497", "Random Person");
        assertEquals(0, validate(ContactDto, ContactDto.Save.class));

        ContactDto.setNumber("+7978");
        assertEquals(0, validate(ContactDto, ContactDto.Save.class));

        ContactDto.setNumber("+79788381");
        assertEquals(0, validate(ContactDto, ContactDto.Save.class));
        
        //null
        ContactDto.setNumber(null);
        assertEquals(1, validate(ContactDto, ContactDto.Save.class));
        
        //min
        ContactDto.setNumber("+797");
        assertEquals(1, validate(ContactDto, ContactDto.Save.class));
        
        //max
        ContactDto.setNumber("+797883814971");
        assertEquals(1, validate(ContactDto, ContactDto.Save.class));
    }

    @Test
    public void testSetName() {
        //valid case
        ContactDto ContactDto = new ContactDto("+79788381497", "Random Person");
        assertEquals(0, validate(ContactDto, ContactDto.Save.class));

        ContactDto.setName("Ran");
        assertEquals(0, validate(ContactDto, ContactDto.Save.class));

        ContactDto.setName("Random");
        assertEquals(0, validate(ContactDto, ContactDto.Save.class));
        
        //null
        ContactDto.setName(null);
        assertEquals(1, validate(ContactDto, ContactDto.Save.class));
        
        //min
        ContactDto.setName("Ra");
        assertEquals(1, validate(ContactDto, ContactDto.Save.class));
        
        //max
        StringBuilder builder = new StringBuilder(70);
        for (int i = 0; i < 7; i++) {
            builder.append("abcdefghij");
        }
        ContactDto.setName(builder.toString());
        assertEquals(1, validate(ContactDto, ContactDto.Save.class));
    }

    /**
     * Validate ContactDto object
     * @return error messages count
     */
    private static int validate(ContactDto ContactDto, Class<?> type) {
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(ContactDto, type);
        return violations.size();
    }
}
