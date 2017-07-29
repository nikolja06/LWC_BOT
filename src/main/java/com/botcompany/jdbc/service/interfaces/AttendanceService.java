package com.botcompany.jdbc.service.interfaces;


import com.botcompany.jdbc.model.Attendance;

import java.sql.Date;
import java.util.List;

public interface AttendanceService {

    void create(Attendance attendance);
    boolean update(Attendance attendance);
    List<Attendance> getAllByUserId(long userId);
    Attendance getLastByUserId(long userId);
    List<Attendance> getLastCountByUserId(int count, long userId);
    boolean deleteAll(long userId);
    boolean deleteByDate(Date date);
    boolean deleteByDateAndUserId(Date date, long userId);
}
