package com.codewarts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    @OneToMany(mappedBy = "staffRole", cascade = CascadeType.ALL)
    private List<Staff> staff;
}
