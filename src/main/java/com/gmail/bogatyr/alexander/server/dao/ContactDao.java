package com.gmail.bogatyr.alexander.server.dao;

import com.gmail.bogatyr.alexander.server.entity.Contact;
import com.google.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.hibernate.criterion.MatchMode.ANYWHERE;

public class ContactDao implements IContactDao {

    @Inject
    private Session session;
    
    @Inject
    private EntityManager em;

    public List<Contact> find(String query) {
        Criteria criteria = session.createCriteria(Contact.class);
        criteria.add(Restrictions.disjunction(
                Restrictions.eq("number", query),
                Restrictions.like("name", query, ANYWHERE)
        ));
        return criteria.list();
    }

    public List<Contact> findAll() {
        em.clear();
        Criteria criteria = session.createCriteria(Contact.class);
        return criteria.list();
    }

    public boolean isNumberExist(String number) {
        if(isEmpty(number)) return false;

        Criteria criteria = session.createCriteria(Contact.class);
        criteria.setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("number", number));

        Long count = (Long) criteria.uniqueResult();
        return count > 0;
    }

    public Contact save(Contact contact) {
        em.persist(contact);
        return contact;
    }
    
    public Contact findById(Long id) {
        em.clear();
        return em.find(Contact.class, id);
    }
    
    public Contact update(Contact contact) {
        Contact contactDb = findById(contact.getId());
        contactDb.setNumber(contact.getNumber());
        contactDb.setName(contact.getName());
        em.merge(contactDb);
        return contact;
    }
    
    public void delete(Contact contact) {
        Contact contactDb = findById(contact.getId());
        em.remove(contactDb);
    }
    
    public boolean contains(Contact contact) {
        return findById(contact.getId()) != null;
    }
}
