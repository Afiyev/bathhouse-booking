package com.bathhouse.booking.model;

import java.util.ArrayList;
import java.util.List;

public class CabinCreationDto {
    private List<Cabin> cabins;
    private Bathhouse bathhouse;
    private int cabin_number;

    public CabinCreationDto() {
        this.cabins = new ArrayList<>();
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

    public List<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(List<Cabin> cabins) {
        this.cabins = cabins;
    }
}
