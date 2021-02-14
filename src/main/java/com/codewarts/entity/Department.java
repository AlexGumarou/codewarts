package com.codewarts.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ChildGroup> childGroup;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Staff> staff;

    public Department() {
    }

    public Department(int id, String name, String address, String phone, List<ChildGroup> childGroup, List<Staff> staff) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.childGroup = childGroup;
        this.staff = staff;
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

    public List<ChildGroup> getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(List<ChildGroup> childGroup) {
        this.childGroup = childGroup;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
}
