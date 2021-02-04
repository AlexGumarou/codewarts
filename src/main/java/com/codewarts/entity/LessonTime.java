package com.codewarts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    @OneToMany(mappedBy = "lessonTime", cascade = CascadeType.ALL)
    private List<Accounting> accounting;

}
