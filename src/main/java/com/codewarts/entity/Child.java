package com.codewarts.entity;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "{child.name.empty}")
    private String name;
    @NotEmpty(message = "{child.surname.empty}")
    private String surname;
    @Column(name = "birthday_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdayDate;

    @OneToMany (mappedBy = "child", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Payment> payment;

    @OneToMany (mappedBy = "child", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Attendance> attendance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "child_group_id")
    private ChildGroup childGroup;

    public Child(String name, String surname, LocalDate birthdayDate, Parent parent, ChildGroup childGroup) {
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
        this.parent = parent;
        this.childGroup = childGroup;
    }

    public Child(String name, String surname, LocalDate birthdayDate, ChildGroup childGroup) {
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
        this.childGroup = childGroup;
    }

    public Child() {
    }

    public Child(int id, String name, String surname, LocalDate birthdayDate, List<Payment> payment,
                 List<Attendance> attendance, Parent parent, ChildGroup childGroup) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
        this.payment = payment;
        this.attendance = attendance;
        this.parent = parent;
        this.childGroup = childGroup;
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

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public ChildGroup getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(ChildGroup childGroup) {
        this.childGroup = childGroup;
    }
}
