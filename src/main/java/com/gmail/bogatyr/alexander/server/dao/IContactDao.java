package com.gmail.bogatyr.alexander.server.dao;

import com.gmail.bogatyr.alexander.server.entity.Contact;

import java.util.List;

public interface IContactDao {
    
    List<Contact> find(String query);

    List<Contact> findAll();

    boolean isNumberExist(String number);

    Contact save(Contact contact);

    Contact findById(Long id);

    Contact update(Contact contact);

    void delete(Contact contact);

    boolean contains(Contact contact);
}
