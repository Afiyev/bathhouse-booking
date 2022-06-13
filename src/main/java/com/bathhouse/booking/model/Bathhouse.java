package com.bathhouse.booking.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bathhouses")
public class Bathhouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone_number;

    @Column
    private String city;

    @Column
    private double rating;

    @OneToMany(mappedBy = "bathhouse", cascade = CascadeType.ALL)
    private Set<Cabin> cabins;

    public Bathhouse() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(Set<Cabin> cabins) {
        this.cabins = cabins;
    }
}
