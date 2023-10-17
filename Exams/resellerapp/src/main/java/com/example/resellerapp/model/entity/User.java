package com.example.resellerapp.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany
    private List<Offer> createdOffers;

    @OneToMany
    private List<Offer> boughtOffers;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Offer> getCreatedOffers() {
        return createdOffers;
    }

    public void setCreatedOffers(List<Offer> createdOffers) {
        this.createdOffers = createdOffers;
    }

    public List<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(List<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }
}


