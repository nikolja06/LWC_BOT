package com.botcompany.jdbc.service.interfaces;


import com.botcompany.jdbc.model.User;

import java.util.List;

public interface UserService {

    void create(User user);

    boolean update(User user);

    boolean delete(User user);

    boolean deleteById(long id);

    User getUserById(long id);

    List<User> getAllUsers();
}
