package com.botcompany.jdbc.service;


import com.botcompany.jdbc.dao.UserDaoIml;
import com.botcompany.jdbc.dao.interfaces.IUserDao;
import com.botcompany.jdbc.model.User;
import com.botcompany.jdbc.service.interfaces.AttendanceService;
import com.botcompany.jdbc.service.interfaces.ContactService;
import com.botcompany.jdbc.service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private IUserDao userDao = new UserDaoIml();
    private AttendanceService attendanceService = new AttendanceServiceImpl();
    private ContactService contactService = new ContactServiceImpl();

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user) && contactService.delete(user) && attendanceService.deleteAll(user.getId());
    }

    @Override
    public boolean deleteById(long id) {
        return userDao.deleteById(id) && contactService.delete(id) && attendanceService.deleteAll(id);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
