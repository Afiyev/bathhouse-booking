package com.bathhouse.booking.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(columnDefinition = "boolean[]")
    @Type(type = "com.vladmihalcea.hibernate.type.array.BooleanArrayType")
    private boolean[] hours;

    @OneToOne(mappedBy = "schedule")
    private Cabin cabin;

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean[] getHours() {
        return hours;
    }

    public void setHours(boolean[] hours) {
        this.hours = hours;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }
}
