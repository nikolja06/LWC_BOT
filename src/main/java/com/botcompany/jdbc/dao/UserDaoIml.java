package com.botcompany.jdbc.dao;


import com.botcompany.jdbc.dao.interfaces.IUserDao;
import com.botcompany.jdbc.model.User;
import com.botcompany.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIml implements IUserDao {

    private String query;

    @Override
    public void create(User user) {
        query = "INSERT INTO users (first_name, last_name) VALUES (?,?)";

        try(Connection connection = ConnectionUtil.connect()){

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong("id"));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(User user) {
        query = "UPDATE USERS SET first_name = ?, last_name = ? WHERE id = ?";

        boolean result = true;

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setLong(3, user.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        query = "DELETE FROM USERS WHERE id = ?";
        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        query = "DELETE FROM USERS WHERE id = ?";
        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserById(long id) {
        query = "SELECT * FROM USERS WHERE id = ?";
        User user = new User();
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = createUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        query = "SELECT * FROM USERS";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User createUser(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setFullName(user.getFirstName() + " " + user.getLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
