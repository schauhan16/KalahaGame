package com.shailendra.model;

public class GameData {
    private int activePlayer;
    private KalahaBoard board;
    private boolean gameOver;
    private int winner;

    public GameData(int id, KalahaBoard board) {
        this.activePlayer = id;
        this.board = board;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }

    public KalahaBoard getBoard() {
        return board;
    }

    public void setBoard(KalahaBoard board) {
        this.board = board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
