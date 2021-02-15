package com.shailendra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.shailendra.config.KalahaGameConfig.NUMBER_OF_MARBLE_PER_PIT;

public class Pit {

    private int noOfMarbles;
    private boolean isBank;
    private int id;

    public Pit(int id) {
        this.id = id;
        noOfMarbles = NUMBER_OF_MARBLE_PER_PIT;
        isBank = false;
    }

    public int getNoOfMarbles() {
        return noOfMarbles;
    }

    public void setNoOfMarbles(int noOfMarbles) {
        this.noOfMarbles = noOfMarbles;
    }

    @JsonIgnore
    public boolean isBank() {
        return isBank;
    }

    public void setBank(boolean bank) {
        isBank = bank;
    }

    public void setEmpty() {
        this.noOfMarbles = 0;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.noOfMarbles == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
