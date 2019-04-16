package com.sofiqul54.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GroupLeaderSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leaderName;

    private double commission;

    private double totalCommission;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "groupleader_bookingsummary", nullable = false)
//    private Groupleader groupleader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilgrim_bookingsummary", nullable = false)
    private Pilgrim pilgrim;


    public GroupLeaderSummary() {
    }

    public GroupLeaderSummary(String leaderName, double commission, double totalCommission, Pilgrim pilgrim) {
        this.leaderName = leaderName;
        this.commission = commission;
        this.totalCommission = totalCommission;
        this.pilgrim = pilgrim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
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
        GroupLeaderSummary that = (GroupLeaderSummary) o;
        return Double.compare(that.commission, commission) == 0 &&
                Double.compare(that.totalCommission, totalCommission) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(leaderName, that.leaderName) &&
                Objects.equals(pilgrim, that.pilgrim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaderName, commission, totalCommission, pilgrim);
    }
}
