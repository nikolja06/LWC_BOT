package com.botcompany.jdbc.dao;


import com.botcompany.jdbc.dao.interfaces.IAttendanceDao;
import com.botcompany.jdbc.model.Attendance;
import com.botcompany.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDaoImpl implements IAttendanceDao{

    String query;

    @Override
    public void create(Attendance attendance) {
        query = "INSERT INTO ATTENDANCE (date, is_present, user_id) VALUES (?,?,?)";

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, attendance.getDate());
            statement.setBoolean(2, attendance.isPresent());
            statement.setLong(3, attendance.getUserId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    attendance.setId(generatedKeys.getLong("id"));
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
    public boolean update(Attendance attendance) {
        query = "UPDATE ATTENDANCE SET date = ?, is_present = ? WHERE user_id = ?";
        boolean result = true;

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query , ResultSet.CONCUR_READ_ONLY);
            statement.setDate(1, attendance.getDate());
            statement.setBoolean(2, attendance.isPresent());
            statement.setLong(3, attendance.getUserId());

            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Attendance> getAllByUserId(long userId) {
        query = "SELECT * FROM ATTENDANCE WHERE user_id = ?";
        List<Attendance> attendances = new ArrayList<>();

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                attendances.add(createAttendance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    @Override
    public Attendance getLastByUserId(long userId) {
        query = "SELECT * FROM ATTENDANCE t " +
                "INNER JOIN (" +
                "    SELECT user_id, max(date) AS MaxDate" +
                "    FROM ATTENDANCE" +
                "    GROUP BY user_id)" +
                " tm ON t.user_id = ? AND t.date = tm.MaxDate";

        Attendance attendance = null;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            attendance = createAttendance(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    @Override
    public List<Attendance> getLastCountByUserId(int count, long userId) {
        query = "SELECT * FROM ATTENDANCE WHERE user_id = ? ORDER BY date DESC LIMIT ?;";

        List<Attendance> attendances = new ArrayList<>();

        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setInt(2, count);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                attendances.add(createAttendance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    @Override
    public boolean deleteAll(long userId) {

        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ATTENDANCE WHERE user_id = ?");
            statement.setLong(1, userId);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteByDate(Date date) {

        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ATTENDANCE WHERE date = ?");
            statement.setDate(1, date);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteByDateAndUserId(Date date, long userId) {

        boolean result = true;
        try (Connection connection = ConnectionUtil.connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ATTENDANCE WHERE date = ? AND user_id = ?");
            statement.setDate(1, date);
            statement.setLong(2, userId);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Attendance createAttendance(ResultSet resultSet){
        Attendance attendance = new Attendance();
        try {
            attendance.setDate(resultSet.getDate("date"));
            attendance.setPresent(resultSet.getBoolean("is_present"));
            attendance.setUserId(resultSet.getLong("user_id"));
            attendance.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }
}
