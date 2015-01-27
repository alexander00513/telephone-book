package com.gmail.bogatyr.alexander.shared.helper;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.gmail.bogatyr.alexander.shared.dto.ContactDto;
import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapperSingletonWrapper;

import java.util.ArrayList;
import java.util.List;

public final class AppHelper {

    public static List<ContactDto> convertToDto(List<Contact> sourceList) {
        if (CollectionUtils.isNotEmpty(sourceList)) {
            List<ContactDto> targetList = new ArrayList<>(sourceList.size());
            for (Contact source : sourceList) {
                targetList.add(DozerBeanMapperSingletonWrapper.getInstance().map(source, ContactDto.class));
            }
            return targetList;
        }
        return null;
    }
    
    public static ContactDto convertToDto(Contact contact) {
        if (contact != null) {
            return DozerBeanMapperSingletonWrapper.getInstance().map(contact, ContactDto.class);
        }
        return null;
    }
    
    
}
