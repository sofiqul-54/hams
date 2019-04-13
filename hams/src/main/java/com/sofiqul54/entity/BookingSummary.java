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

    private double collectionAmount;

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


    public BookingSummary(double totalAmount, double paidAmount, double dueAmount, double collectionAmount, Pilgrim pilgrim, Ppackage ppackage) {
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.collectionAmount = collectionAmount;
        this.pilgrim = pilgrim;
        this.ppackage = ppackage;
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

    public double getCollectionAmount() {
        return collectionAmount;
    }

    public void setCollectionAmount(double collectionAmount) {
        this.collectionAmount = collectionAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingSummary that = (BookingSummary) o;
        return Double.compare(that.totalAmount, totalAmount) == 0 &&
                Double.compare(that.paidAmount, paidAmount) == 0 &&
                Double.compare(that.dueAmount, dueAmount) == 0 &&
                Double.compare(that.collectionAmount, collectionAmount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(pilgrim, that.pilgrim) &&
                Objects.equals(ppackage, that.ppackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalAmount, paidAmount, dueAmount, collectionAmount, pilgrim, ppackage);
    }
}
