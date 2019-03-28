package com.sofiqul54.entity;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pilgrim")
public class Pilgrim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pilgrimNumber;

    @Size(min = 2,max = 50,message = "Hey, Size must be between 2 and 50")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String motherName;

    @Column(nullable = false)
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regiDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate = new Date();

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String nid;

    private String email;

    private String maritalStatus;//check or radio status


    private String spouseName;


    private String occupation;


    private String address;

    @Column(nullable = false)
    private String district; // dropdown hobe

    private double bookingAmount;
    private double tolalAmount;

    //////File Upload==============
    @Column(nullable = true)
    private long fileSize;
    private String fileName;
    //  @Lob
    // private byte[] file;

    private String filePath;
    private String fileExtension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id", nullable = false)
    private Ppackage ppackage;//dropdown hobe



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gl_id", nullable = false)
    private Groupleader groupleader; //dropdown hobe


    public Pilgrim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPilgrimNumber() {
        return pilgrimNumber;
    }

    public void setPilgrimNumber(String pilgrimNumber) {
        this.pilgrimNumber = pilgrimNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(Date regiDate) {
        this.regiDate = regiDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public double getTolalAmount() {
        return tolalAmount;
    }

    public void setTolalAmount(double tolalAmount) {
        this.tolalAmount = tolalAmount;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Ppackage getPpackage() {
        return ppackage;
    }

    public void setPpackage(Ppackage ppackage) {
        this.ppackage = ppackage;
    }

    public Groupleader getGroupleader() {
        return groupleader;
    }

    public void setGroupleader(Groupleader groupleader) {
        this.groupleader = groupleader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilgrim pilgrim = (Pilgrim) o;
        return Double.compare(pilgrim.bookingAmount, bookingAmount) == 0 &&
                Double.compare(pilgrim.tolalAmount, tolalAmount) == 0 &&
                fileSize == pilgrim.fileSize &&
                Objects.equals(id, pilgrim.id) &&
                Objects.equals(pilgrimNumber, pilgrim.pilgrimNumber) &&
                Objects.equals(name, pilgrim.name) &&
                Objects.equals(fatherName, pilgrim.fatherName) &&
                Objects.equals(motherName, pilgrim.motherName) &&
                Objects.equals(gender, pilgrim.gender) &&
                Objects.equals(regiDate, pilgrim.regiDate) &&
                Objects.equals(lastModifiedDate, pilgrim.lastModifiedDate) &&
                Objects.equals(birthDate, pilgrim.birthDate) &&
                Objects.equals(nid, pilgrim.nid) &&
                Objects.equals(email, pilgrim.email) &&
                Objects.equals(maritalStatus, pilgrim.maritalStatus) &&
                Objects.equals(spouseName, pilgrim.spouseName) &&
                Objects.equals(occupation, pilgrim.occupation) &&
                Objects.equals(address, pilgrim.address) &&
                Objects.equals(district, pilgrim.district) &&
                Objects.equals(fileName, pilgrim.fileName) &&
                Objects.equals(filePath, pilgrim.filePath) &&
                Objects.equals(fileExtension, pilgrim.fileExtension) &&
                Objects.equals(ppackage, pilgrim.ppackage) &&
                Objects.equals(groupleader, pilgrim.groupleader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pilgrimNumber, name, fatherName, motherName, gender, regiDate, lastModifiedDate, birthDate, nid, email, maritalStatus, spouseName, occupation, address, district, bookingAmount, tolalAmount, fileSize, fileName, filePath, fileExtension, ppackage, groupleader);
    }
}
