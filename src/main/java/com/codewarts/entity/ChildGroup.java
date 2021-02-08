package com.codewarts.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "child_group")
public class ChildGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany (mappedBy = "childGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Child> child;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany (mappedBy = "childGroup", cascade = CascadeType.ALL)
    private List<Accounting> accounting;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public ChildGroup() {
    }

    public ChildGroup(int id, String name, List<Child> child, List<Accounting> accounting, Department department) {
        this.id = id;
        this.name = name;
        this.child = child;
        this.accounting = accounting;
        this.department = department;
    }

    public ChildGroup(String name, Department department) {
        this.name = name;
        this.department = department;
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

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }

    public List<Accounting> getAccounting() {
        return accounting;
    }

    public void setAccounting(List<Accounting> accounting) {
        this.accounting = accounting;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
