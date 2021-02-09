package com.codewarts.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String name;
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String surname;
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String login;
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String pass;
    @NotEmpty(message = "Это поле не должно быть пустым")
    @Email(message = "Поле должно быть вида abc@mail.ru")
    private String email;
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String address;
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String phone;
    @Column(name = "phone_additional")
    private String phoneAdditional;
    @Column(name = "price_per_hour")
    private String pricePerHour;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private StaffRole staffRole;

    public Staff() {
    }

    public Staff(int id, @NotEmpty(message = "Это поле не должно быть пустым") String name, @NotEmpty(message = "Это поле не должно быть пустым") String surname, @NotEmpty(message = "Это поле не должно быть пустым") String login, @NotEmpty(message = "Это поле не должно быть пустым") String pass, @NotEmpty(message = "Это поле не должно быть пустым") @Email(message = "Поле должно быть вида abc@mail.ru") String email, @NotEmpty(message = "Это поле не должно быть пустым") String address, @NotEmpty(message = "Это поле не должно быть пустым") String phone, String phoneAdditional, String pricePerHour, Department department, StaffRole staffRole) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.phoneAdditional = phoneAdditional;
        this.pricePerHour = pricePerHour;
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

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
