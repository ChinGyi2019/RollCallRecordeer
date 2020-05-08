package com.project.group7.rollcall.model;

public class Student {

    String id,roll,name,year,address,ph;

    public Student(){}

    public Student(String roll, String name, String year, String address, String ph) {
        this.roll = roll;
        this.name = name;
        this.year = year;
        this.address = address;
        this.ph = ph;
    }

    public Student(String id, String roll, String name, String year, String address, String ph) {
        this.id = id;
        this.roll = roll;
        this.name = name;
        this.year = year;
        this.address = address;
        this.ph = ph;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }
}
