package com.codewarts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "child_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany (mappedBy = "childGroup", cascade = CascadeType.ALL)
    private List<Child> child;
    @OneToMany (mappedBy = "childGroup", cascade = CascadeType.ALL)
    private List<Accounting> accounting;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
