package com.codewarts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<ChildGroup> childGroup;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Staff> staff;

}
