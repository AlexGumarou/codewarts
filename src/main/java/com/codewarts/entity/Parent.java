package com.codewarts.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mother;
    private String father;
    @Column(name = "phone_mother")
    private String phoneMother;
    @Column(name = "phone_father")
    private String phoneFather;

    @OneToMany (mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Child> child;

    public Parent(String mother, String father, String phoneMother, String phoneFather) {
        this.mother = mother;
        this.father = father;
        this.phoneMother = phoneMother;
        this.phoneFather = phoneFather;
    }

    public Parent() {
    }

    public Parent(int id, String mother, String father, String phoneMother, String phoneFather, List<Child> child) {
        this.id = id;
        this.mother = mother;
        this.father = father;
        this.phoneMother = phoneMother;
        this.phoneFather = phoneFather;
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getPhoneMother() {
        return phoneMother;
    }

    public void setPhoneMother(String phoneMother) {
        this.phoneMother = phoneMother;
    }

    public String getPhoneFather() {
        return phoneFather;
    }

    public void setPhoneFather(String phoneFather) {
        this.phoneFather = phoneFather;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }
}
