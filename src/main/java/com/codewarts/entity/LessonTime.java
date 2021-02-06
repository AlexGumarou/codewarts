package com.codewarts.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson_time")
public class LessonTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    @OneToMany(mappedBy = "lessonTime", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Accounting> accounting;

    public LessonTime() {
    }

    public LessonTime(int id, String type, List<Accounting> accounting) {
        this.id = id;
        this.type = type;
        this.accounting = accounting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Accounting> getAccounting() {
        return accounting;
    }

    public void setAccounting(List<Accounting> accounting) {
        this.accounting = accounting;
    }
}
