package com.bathhouse.booking.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Arrays;
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
    @Column(name = "isbooked",
            columnDefinition = "BOOLEAN[]")
    private List<Boolean> isBooked;

    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cabin cabin;

    public Schedule() {
        Boolean[] array = new Boolean[12];
        Arrays.fill(array, false);
        isBooked = Arrays.asList(array);
    }

    public List<Boolean> getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(List<Boolean> isBooked) {
        this.isBooked = isBooked;
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
