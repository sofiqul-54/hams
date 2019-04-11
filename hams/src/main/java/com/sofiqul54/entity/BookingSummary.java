package com.sofiqul54.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookingSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;

    private double paidAmount;

    private double dueAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booksummary_pilgrim", nullable = false)
    private Pilgrim pilgrim;//dropdown hobe

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booksummary_package", nullable = false)
    private Ppackage ppackage;//dropdown hobe

    public BookingSummary() {
    }

    public BookingSummary(double totalAmount, double paidAmount, double dueAmount, Pilgrim pilgrim, Ppackage ppackage) {
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.pilgrim = pilgrim;
        this.ppackage = ppackage;
    }

    public BookingSummary(double totalAmount, double paidAmount, double dueAmount, Pilgrim pilgrim) {
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.pilgrim = pilgrim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Pilgrim getPilgrim() {
        return pilgrim;
    }

    public void setPilgrim(Pilgrim pilgrim) {
        this.pilgrim = pilgrim;
    }

    public Ppackage getPpackage() {
        return ppackage;
    }

    public void setPpackage(Ppackage ppackage) {
        this.ppackage = ppackage;
    }
}
