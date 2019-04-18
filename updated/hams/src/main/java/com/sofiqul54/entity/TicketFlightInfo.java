package com.sofiqul54.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ticket_flight")
public class TicketFlightInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticketNo;

    private String tikAgnName;

    @Column(nullable = false, unique = true)
    private String flightNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date flightDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date returnDate;

    @OneToOne
    @JoinColumn(name = "pilg_id")
    private Pilgrim pilgrim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTikAgnName() {
        return tikAgnName;
    }

    public void setTikAgnName(String tikAgnName) {
        this.tikAgnName = tikAgnName;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
        TicketFlightInfo that = (TicketFlightInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ticketNo, that.ticketNo) &&
                Objects.equals(tikAgnName, that.tikAgnName) &&
                Objects.equals(flightNo, that.flightNo) &&
                Objects.equals(flightDate, that.flightDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                Objects.equals(pilgrim, that.pilgrim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketNo, tikAgnName, flightNo, flightDate, returnDate, pilgrim);
    }
}
