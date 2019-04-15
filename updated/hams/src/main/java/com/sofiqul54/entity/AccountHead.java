package com.sofiqul54.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account_head")
public class AccountHead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String accountname;
    private String description;

    public AccountHead() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHead that = (AccountHead) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(accountname, that.accountname) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountname, description);
    }
}
