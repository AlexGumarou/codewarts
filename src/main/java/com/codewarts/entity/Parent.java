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
    @OneToMany (mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> child;
}
