package com.botcompany.jdbc.model;


import java.sql.Date;

public class Attendance {

    private Long id;
    private Date date;
    private boolean isPresent;
    private long userId;

    public Attendance() {

    }

    public Attendance(Date date, boolean isPresent, long userId) {
        this.date = date;
        this.isPresent = isPresent;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public long getUserId() {return userId;}

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendance that = (Attendance) o;

        if (isPresent != that.isPresent) return false;
        if (userId != that.userId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (isPresent ? 1 : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", isPresent=" + isPresent +
                ", userId=" + userId +
                '}';
    }
}
