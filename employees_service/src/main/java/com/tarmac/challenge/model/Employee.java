package com.tarmac.challenge.model;
import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="name")
    private String name;

    @Column(name = "role", nullable = true)
    private String role;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "picture_url", nullable = true)
    private String picture_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
