package com.sofiqul54.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "passport_info")
public class PassportInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date issueDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expDate;

    private String dip;

    @OneToOne
    @JoinColumn(name = "passport_pilgrim", nullable = false)
    private Pilgrim pilgrim;

    public PassportInfo() {
    }

    public PassportInfo(String passportNo, Date issueDate, Date expDate, String dip, Pilgrim pilgrim) {
        this.passportNo = passportNo;
        this.issueDate = issueDate;
        this.expDate = expDate;
        this.dip = dip;
        this.pilgrim = pilgrim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public Pilgrim getPilgrim() {
        return pilgrim;
    }

    public void setPilgrim(Pilgrim pilgrim) {
        this.pilgrim = pilgrim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportInfo that = (PassportInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(passportNo, that.passportNo) &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(expDate, that.expDate) &&
                Objects.equals(dip, that.dip) &&
                Objects.equals(pilgrim, that.pilgrim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportNo, issueDate, expDate, dip, pilgrim);
    }
}
