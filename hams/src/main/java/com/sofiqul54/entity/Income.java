package com.sofiqul54.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String accountTitle;

    private double amount;

    private double totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cDate;

    @ManyToOne
    @JoinColumn(name = "pilgm_id", nullable = false)
    private Pilgrim pilgrim;

    @ManyToOne
    @JoinColumn(name = "income_package", nullable = false)
    private Ppackage ppackage;

    @ManyToMany
    @JoinTable(
            name = "income_groupleader",
            joinColumns = @JoinColumn(name = "inc_id"),
            inverseJoinColumns = @JoinColumn(name = "groupleader_id"))
    private Set<Groupleader> groupleaders;

    @ManyToMany
    @JoinTable(
            name = "acc_head",
            joinColumns = @JoinColumn(name = "inc_id"),
            inverseJoinColumns = @JoinColumn(name = "acc_id"))
            private Set<AccountHead> accountHeads;


    public Income() {
    }

    public Income(String accountTitle, double amount, double totalAmount, Date cDate, Pilgrim pilgrim, Ppackage ppackage, Set<Groupleader> groupleaders, Set<AccountHead> accountHeads) {
        this.accountTitle = accountTitle;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.cDate = cDate;
        this.pilgrim = pilgrim;
        this.ppackage = ppackage;
        this.groupleaders = groupleaders;
        this.accountHeads = accountHeads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
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

    public Set<Groupleader> getGroupleaders() {
        return groupleaders;
    }

    public void setGroupleaders(Set<Groupleader> groupleaders) {
        this.groupleaders = groupleaders;
    }

    public Set<AccountHead> getAccountHeads() {
        return accountHeads;
    }

    public void setAccountHeads(Set<AccountHead> accountHeads) {
        this.accountHeads = accountHeads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Double.compare(income.amount, amount) == 0 &&
                Double.compare(income.totalAmount, totalAmount) == 0 &&
                Objects.equals(id, income.id) &&
                Objects.equals(accountTitle, income.accountTitle) &&
                Objects.equals(cDate, income.cDate) &&
                Objects.equals(pilgrim, income.pilgrim) &&
                Objects.equals(ppackage, income.ppackage) &&
                Objects.equals(groupleaders, income.groupleaders) &&
                Objects.equals(accountHeads, income.accountHeads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountTitle, amount, totalAmount, cDate, pilgrim, ppackage, groupleaders, accountHeads);
    }
}
