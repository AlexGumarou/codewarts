package com.codewarts.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(name = "birthday_date")
    private Date birthdayDate;
    private String login;
    private String pass;
    private String email;
    private String address;
    private String phone;
    @Column(name = "phone_additional")
    private String phoneAdditional;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private StaffRole staffRole;

    public Staff() {
    }

    public Staff(int id, String name, String surname, Date birthdayDate, String login, String pass, String email, String address, String phone, String phoneAdditional, Department department, StaffRole staffRole) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.phoneAdditional = phoneAdditional;
        this.department = department;
        this.staffRole = staffRole;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneAdditional() {
        return phoneAdditional;
    }

    public void setPhoneAdditional(String phoneAdditional) {
        this.phoneAdditional = phoneAdditional;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }
}
