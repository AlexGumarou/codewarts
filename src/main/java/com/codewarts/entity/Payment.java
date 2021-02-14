package com.codewarts.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    private String sum;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    private Child child;

    public Payment() {
    }

    public Payment(int id, LocalDate paymentDate, String sum, Child child) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.sum = sum;
        this.child = child;
    }

    public Payment(LocalDate paymentDate, String sum, Child child) {
        this.paymentDate = paymentDate;
        this.sum = sum;
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Дата=" + paymentDate + ", сумма=" + sum;
    }
}
