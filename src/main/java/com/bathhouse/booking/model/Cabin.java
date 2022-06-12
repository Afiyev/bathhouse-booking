package com.bathhouse.booking.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cabins")
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String type;

    @Column
    private int price;

    @Column
    private int bathhouse_id;

    @Column
    private int schedule_id;

    @ManyToOne
    @JoinColumn(name = "bathhouse_id")
    private Bathhouse bathhouse;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToMany(mappedBy = "cabin")
    private Set<Reservation> reservations;

    public Cabin() {
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public int getBathhouse_id() {
        return bathhouse_id;
    }

    public void setBathhouse_id(int bathhouse_id) {
        this.bathhouse_id = bathhouse_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Bathhouse getBathhouse() {
        return bathhouse;
    }

    public void setBathhouse(Bathhouse bathhouse) {
        this.bathhouse = bathhouse;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
