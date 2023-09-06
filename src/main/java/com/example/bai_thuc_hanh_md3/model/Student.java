package com.example.bai_thuc_hanh_md3.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private String email;
    private LocalDate DOB;
    private String address;
    private String phoneNumber;
    private Classroom classroom;

    public Student(int id, String name, String email, LocalDate DOB, String address, String phoneNumber, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classroom = classroom;
    }

    public Student(String name, String email, LocalDate DOB, String address, String phoneNumber, Classroom classroom) {
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classroom = classroom;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
