package com.botcompany.jdbc.dao.interfaces;


import com.botcompany.jdbc.model.Contact;
import com.botcompany.jdbc.model.User;

import java.util.List;

public interface IContactDao {

    void create(Contact contact);

    boolean update(Contact contact);

    boolean delete(Contact contact);

    boolean delete(User user);

    boolean delete(long userId);

    List<Contact> getAllByUserId(long userId);

}
