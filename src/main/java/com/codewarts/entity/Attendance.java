package com.codewarts.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    private Child child;

    public Attendance(LocalDate date, Child child) {
        this.date = date;
        this.child = child;
    }

    public Attendance() {

    }

    public Attendance(int id, LocalDate date, Child child) {
        this.id = id;
        this.date = date;
        this.child = child;
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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return " " + date;
    }
}
