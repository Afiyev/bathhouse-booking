package com.bathhouse.booking.model;

import javax.persistence.*;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne
    @JoinColumn(name = "bathhouse_id")
    private Bathhouse bathhouse;

    public Recommendation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bathhouse getBathhouse() {
        return bathhouse;
    }

    public void setBathhouse(Bathhouse bathhouse) {
        this.bathhouse = bathhouse;
    }
}
