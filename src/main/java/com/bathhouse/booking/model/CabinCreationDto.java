package com.bathhouse.booking.model;

import java.util.HashSet;
import java.util.Set;

public class CabinCreationDto {
    private Set<Cabin> cabins;
    private Bathhouse bathhouse;
    private int cabin_number;

    public CabinCreationDto() {
        this.cabins = new HashSet<>();
        this.cabin_number = 0;
        this.bathhouse = new Bathhouse();
    }

    public void addCabin(Cabin cabin){
        this.cabins.add(cabin);
    }

    public boolean isSetEmpty(){
        return this.cabins.isEmpty();
    }

    public Bathhouse getBathhouse() {
        return bathhouse;
    }

    public void setBathhouse(Bathhouse bathhouse) {
        this.bathhouse = bathhouse;
    }

    public int getCabin_number() {
        return cabin_number;
    }

    public void setCabin_number(int cabin_number) {
        this.cabin_number = cabin_number;
    }

    public Set<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(Set<Cabin> cabins) {
        this.cabins = cabins;
    }
}
