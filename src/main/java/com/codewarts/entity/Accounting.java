package com.codewarts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "child_group_id")
    private ChildGroup childGroup;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "lesson_time_id")
    private LessonTime lessonTime;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    public Accounting(LocalDate date, ChildGroup childGroup, Staff staff, LessonTime lessonTime, Theme theme) {
        this.date = date;
        this.childGroup = childGroup;
        this.staff = staff;
        this.lessonTime = lessonTime;
        this.theme = theme;
    }
}
