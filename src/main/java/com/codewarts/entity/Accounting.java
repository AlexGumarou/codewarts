package com.codewarts.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity

public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "child_group_id")
    private ChildGroup childGroup;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    public Accounting(LocalDate date, ChildGroup childGroup, Staff staff, Theme theme) {
        this.date = date;
        this.childGroup = childGroup;
        this.staff = staff;
        this.theme = theme;
    }

    public Accounting() {
    }

    public Accounting(int id, LocalDate date, ChildGroup childGroup, Staff staff, Theme theme) {
        this.id = id;
        this.date = date;
        this.childGroup = childGroup;
        this.staff = staff;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ChildGroup getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(ChildGroup childGroup) {
        this.childGroup = childGroup;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
