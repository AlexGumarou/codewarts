package com.codewarts.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
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
