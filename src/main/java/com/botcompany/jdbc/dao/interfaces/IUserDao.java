package com.botcompany.jdbc.dao.interfaces;


import com.botcompany.jdbc.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface IUserDao {

    void create(User user);

    boolean update(User user);

    boolean delete(User user);

    boolean deleteById(long id);

    User getUserById(long id);

    List<User> getAllUsers();
}
