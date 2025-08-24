package com.example.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the Employee entity in the database.
 * Mapped to the "EMPLOYEE" table.
 */
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer employeeId;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    /**
     * One-to-Many relationship with AccountEntity.
     * cascade=CascadeType.ALL: Operations (persist, merge, remove, refresh, detach) will cascade to associated Account entities.
     * orphanRemoval=true: If an account is removed from the 'accounts' set, it will be deleted from the database.
     * @JoinColumn(name="EMPLOYEE_ID"): Specifies the foreign key column in the 'ACCOUNT' table that links back to 'EMPLOYEE'.
     * This makes the relationship unidirectional from Employee to Account, with the foreign key managed by Employee.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EMPLOYEE_ID") // This maps the foreign key in the 'ACCOUNT' table
    private Set<AccountEntity> accounts = new HashSet<>(); // Initialize to prevent NullPointerException

    // Default constructor for Hibernate
    public EmployeeEntity() {
    }

    // Constructor for convenience
    public EmployeeEntity(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    /**
     * Helper method to add an account to the employee's accounts.
     * This is good practice to ensure consistency if the relationship were bidirectional.
     * However, with @JoinColumn on the @OneToMany side, Hibernate manages the foreign key
     * automatically when the parent is saved.
     * @param account The account to add.
     */
    public void addAccount(AccountEntity account) {
        this.accounts.add(account);
        // If it was a bidirectional relationship, you'd also set the employee on the account:
        // account.setEmployee(this);
    }

    /**
     * Helper method to remove an account from the employee's accounts.
     * @param account The account to remove.
     */
    public void removeAccount(AccountEntity account) {
        this.accounts.remove(account);
        // If it was a bidirectional relationship, you'd also remove the employee from the account:
        // account.setEmployee(null);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accounts size=" + (accounts != null ? accounts.size() : 0) +
                '}';
    }
}
