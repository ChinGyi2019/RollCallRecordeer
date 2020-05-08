package com.project.group7.rollcall.model;

public class Attendance {

    String id,roll,student,attendance;

    public Attendance(){}

    public Attendance(String id, String roll, String student, String attendance) {
        this.id = id;
        this.roll = roll;
        this.student = student;
        this.attendance = attendance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
