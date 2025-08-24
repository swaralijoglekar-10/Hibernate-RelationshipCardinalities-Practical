package com.multipleNamedQueries;

import jakarta.persistence.*;

@Entity(name = "purchaser")
@Table(name = "purchaser")

//Using @NamedQuery for single JPQL or HQL
//@NamedQuery(name = "GET_CUSTOMERS_COUNT", query = "select count(1) from customer")
@NamedQueries({ @NamedQuery(name = "GET_PURCHASERS_COUNT", query = "select count(1) from purchaser"),
        @NamedQuery(name = "GET_ALL_PURCHASERS", query = "from purchaser"),

})


public class Purchaser {
    public Purchaser() {

    }
    public Purchaser(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Column(name = "NAME")
    private String name;

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

    @Override
    public String toString() {
        return "Purchasers [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}