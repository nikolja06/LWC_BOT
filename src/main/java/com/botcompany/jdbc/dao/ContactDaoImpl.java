package com.botcompany.jdbc.dao;

import com.botcompany.jdbc.dao.interfaces.IContactDao;
import com.botcompany.jdbc.model.Contact;
import com.botcompany.jdbc.model.User;
import com.botcompany.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ContactDaoImpl implements IContactDao {


    private String query;

    @Override
    public void create(Contact contact) {
        query = "INSERT INTO CONTACTS (address, phone_number, user_id) VALUES (?,?,?)";

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, contact.getAddress());
            statement.setString(2, contact.getPhoneNumber());
            statement.setLong(3, contact.getUserId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contact.setId(generatedKeys.getLong("id"));
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
    public boolean update(Contact contact) {
        query = "UPDATE CONTACTS SET address = ?, phone_number = ? WHERE user_id = ?";
        boolean result = true;

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getAddress());
            statement.setString(2, contact.getPhoneNumber());
            statement.setLong(3, contact.getUserId());

            result = statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Contact contact) {
        query = "DELETE FROM CONTACTS WHERE user_id = ?";
        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, contact.getUserId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM CONTACTS WHERE user_id = ?");
            statement.setLong(1, user.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(long userId) {
        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM CONTACTS WHERE user_id = ?");
            statement.setLong(1, userId);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Contact> getAllByUserId(long userId) {
        query = "SELECT * FROM CONTACTS WHERE user_id = ?";
        List<Contact> contacts = new ArrayList<>();

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                contacts.add(createContact(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }


    private Contact createContact(ResultSet resultSet){
        Contact contact = new Contact();
        try {
            contact.setId(resultSet.getLong("id"));
            contact.setAddress(resultSet.getString("address"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));
            contact.setUserId(resultSet.getLong("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }
}
