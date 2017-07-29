package com.botcompany.jdbc.service;


import com.botcompany.jdbc.dao.AttendanceDaoImpl;
import com.botcompany.jdbc.dao.interfaces.IAttendanceDao;
import com.botcompany.jdbc.model.Attendance;
import com.botcompany.jdbc.service.interfaces.AttendanceService;

import java.sql.Date;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService{

    private IAttendanceDao attendanceDao = new AttendanceDaoImpl();

    @Override
    public void create(Attendance attendance) {
        attendanceDao.create(attendance);
    }

    @Override
    public boolean update(Attendance attendance) {
        return attendanceDao.update(attendance);
    }

    @Override
    public List<Attendance> getAllByUserId(long userId) {
        return attendanceDao.getAllByUserId(userId);
    }

    @Override
    public Attendance getLastByUserId(long userId) {
        return attendanceDao.getLastByUserId(userId);
    }

    @Override
    public List<Attendance> getLastCountByUserId(int count, long userId) {
        return attendanceDao.getLastCountByUserId(count, userId);
    }

    @Override
    public boolean deleteAll(long userId) {
        return attendanceDao.deleteAll(userId);
    }

    @Override
    public boolean deleteByDate(Date date) {
        return attendanceDao.deleteByDate(date);
    }

    @Override
    public boolean deleteByDateAndUserId(Date date, long userId) {
        return attendanceDao.deleteByDateAndUserId(date, userId);
    }
}
