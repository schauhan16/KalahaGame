package com.shailendra.model;

public class Player {

    private boolean isActive;
    private int playerNumber;

    public Player(int playerNumber) {
        this();
        this.playerNumber = playerNumber;
    }

    public Player() {
        this.isActive = false;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
