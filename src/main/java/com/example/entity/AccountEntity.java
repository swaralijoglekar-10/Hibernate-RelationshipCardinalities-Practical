package com.example.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Represents the Account entity in the database.
 * Mapped to the "ACCOUNT" table.
 */
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer accountId;

    @Column(name = "ACC_NUMBER", nullable = false, length = 100)
    private String accNumber;

    // Default constructor for Hibernate
    public AccountEntity() {
    }

    // Constructor for convenience
    public AccountEntity(String accNumber) {
        this.accNumber = accNumber;
    }

    // Getters and Setters

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountId=" + accountId +
                ", accNumber='" + accNumber + '\'' +
                '}';
    }
}
