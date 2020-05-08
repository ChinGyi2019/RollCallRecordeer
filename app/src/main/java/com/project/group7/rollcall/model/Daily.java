package com.project.group7.rollcall.model;

public class Daily {

    String id,date,students,presents,subject,count;

    public Daily(){}

    public Daily(String date, String students, String presents, String subject, String count) {
        this.date = date;
        this.students = students;
        this.presents = presents;
        this.subject = subject;
        this.count = count;
    }

    public Daily(String id, String date, String students, String presents, String subject, String count) {
        this.id = id;
        this.date = date;
        this.students = students;
        this.presents = presents;
        this.subject = subject;
        this.count = count;
    }

    public String getPresents() {
        return presents;
    }

    public void setPresents(String presents) {
        this.presents = presents;
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

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
