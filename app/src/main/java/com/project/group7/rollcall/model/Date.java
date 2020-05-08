package com.project.group7.rollcall.model;

public class Date {

    String id,date,subjects,found;

    public Date(){}

    public Date(String date, String subjects) {
        this.date = date;
        this.subjects = subjects;
    }

    public Date(String id, String date, String subjects) {
        this.id = id;
        this.date = date;
        this.subjects = subjects;
    }

    public Date(String id, String date, String subjects, String found) {
        this.id = id;
        this.date = date;
        this.subjects = subjects;
        this.found = found;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }
}
