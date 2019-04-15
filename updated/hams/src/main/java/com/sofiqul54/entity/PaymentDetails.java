package com.sofiqul54.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;

    private double bookingAmount;

    private double dueAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booksummary_pilgrim", nullable = false)
    private Pilgrim pilgrim;//dropdown hobe

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booksummary_package", nullable = false)
    private Ppackage ppackage;//dropdown hobe

    public PaymentDetails() {
    }

    public PaymentDetails(double totalAmount, double bookingAmount, double dueAmount, Pilgrim pilgrim, Ppackage ppackage) {
        this.totalAmount = totalAmount;
        this.bookingAmount = bookingAmount;
        this.dueAmount = dueAmount;
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

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDetails that = (PaymentDetails) o;
        return Double.compare(that.totalAmount, totalAmount) == 0 &&
                Double.compare(that.bookingAmount, bookingAmount) == 0 &&
                Double.compare(that.dueAmount, dueAmount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(pilgrim, that.pilgrim) &&
                Objects.equals(ppackage, that.ppackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalAmount, bookingAmount, dueAmount, pilgrim, ppackage);
    }
}
