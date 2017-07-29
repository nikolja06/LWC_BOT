package com.botcompany.jdbc.service;


import com.botcompany.jdbc.dao.ContactDaoImpl;
import com.botcompany.jdbc.dao.interfaces.IContactDao;
import com.botcompany.jdbc.model.Contact;
import com.botcompany.jdbc.model.User;
import com.botcompany.jdbc.service.interfaces.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService{

    private IContactDao contactDao = new ContactDaoImpl();

    @Override
    public void create(Contact contact) {
        contactDao.create(contact);
    }

    @Override
    public boolean update(Contact contact) {
        return contactDao.update(contact);
    }

    @Override
    public boolean delete(Contact contact) {
        return contactDao.delete(contact);
    }

    @Override
    public boolean delete(User user) {
        return contactDao.delete(user);
    }

    @Override
    public boolean delete(long userId) {
        return contactDao.delete(userId);
    }

    @Override
    public List<Contact> getAllByUserId(long userId) {
        return contactDao.getAllByUserId(userId);
    }
}
