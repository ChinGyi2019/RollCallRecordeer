package com.project.group7.rollcall.model;

public class ShowAttendance {

    String id,roll,name,percent;
    int total;

    public ShowAttendance(){}

    public ShowAttendance(String id, String roll, String name, String percent, int total) {
        this.id = id;
        this.roll = roll;
        this.name = name;
        this.percent = percent;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
