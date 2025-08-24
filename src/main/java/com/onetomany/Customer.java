package com.onetomany;
import jakarta.persistence.*;
import java.util.List;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer {
    public Customer() {

    }
    public Customer(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "AGE")
    private int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Id
    @Column(name = "ID")
    private int id;

}