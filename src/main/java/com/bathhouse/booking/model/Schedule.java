package com.bathhouse.booking.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedules")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Type(type = "list-array")
    @Column(name = "hours",
            columnDefinition = "BOOLEAN[]")
    private List<Boolean> hours;

    @OneToOne(mappedBy = "schedule")
    private Cabin cabin;

    public Schedule() {
    }

    public List<Boolean> getHours() {
        return hours;
    }

    public void setHours(List<Boolean> hours) {
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }
}
