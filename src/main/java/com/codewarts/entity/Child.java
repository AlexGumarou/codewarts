package com.codewarts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "child")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Column(name = "birthday_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdayDate;
    @OneToMany (mappedBy = "child", cascade = CascadeType.ALL)
    private List<Payment> payment;
    @OneToMany (mappedBy = "child", cascade = CascadeType.ALL)
    private List<Attendance> attendance;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
    @ManyToOne
    @JoinColumn(name = "child_group_id")
    private ChildGroup childGroup;
}
