package com.codewarts.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Accounting> accounting;
    private String name;

    public Theme() {
    }

    public Theme(int id, List<Accounting> accounting, String name) {
        this.id = id;
        this.accounting = accounting;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Accounting> getAccounting() {
        return accounting;
    }

    public void setAccounting(List<Accounting> accounting) {
        this.accounting = accounting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
