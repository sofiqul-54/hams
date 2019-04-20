package com.sofiqul54.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "visa_info")
public class VisaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date applyDate;

    @Column(unique = true, nullable = false)
    private String visaNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date vIssueDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date vValidityDate;

    private String status;

    @OneToOne
    @JoinColumn(name = "pil_id")
    private Pilgrim pilgrim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getVisaNo() {
        return visaNo;
    }

    public void setVisaNo(String visaNo) {
        this.visaNo = visaNo;
    }

    public Date getvIssueDate() {
        return vIssueDate;
    }

    public void setvIssueDate(Date vIssueDate) {
        this.vIssueDate = vIssueDate;
    }

    public Date getvValidityDate() {
        return vValidityDate;
    }

    public void setvValidityDate(Date vValidityDate) {
        this.vValidityDate = vValidityDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        VisaInfo visaInfo = (VisaInfo) o;
        return Objects.equals(id, visaInfo.id) &&
                Objects.equals(applyDate, visaInfo.applyDate) &&
                Objects.equals(visaNo, visaInfo.visaNo) &&
                Objects.equals(vIssueDate, visaInfo.vIssueDate) &&
                Objects.equals(vValidityDate, visaInfo.vValidityDate) &&
                Objects.equals(status, visaInfo.status) &&
                Objects.equals(pilgrim, visaInfo.pilgrim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applyDate, visaNo, vIssueDate, vValidityDate, status, pilgrim);
    }
}
