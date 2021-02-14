package com.shailendra.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KalahaGame {

    @Autowired
    private KalahaBoard kalahaBoard;

    @Autowired
    private PlayerManager playerManager;

    @Autowired
    private PlayerToPitMapper mapper;

    public Pit getBankForPlayer(Player player) {
        return mapper.getBankForPlayer(player);
    }

    public List<Pit> getPitsForPlayer(Player player) {
        return mapper.getPitsForPlayer(player);
    }

    public void depositMarbleInBank(Player player, int count) {
        Pit bankForPlayer = getBankForPlayer(player);
        bankForPlayer.setNoOfMarbles(bankForPlayer.getNoOfMarbles() + count);
    }

    public boolean isOpponentBank(Player player, int pitId) {
        Pit ownBank = getBankForPlayer(player);
        Pit pit = getBoard().getPitById(pitId);

        return pit.isBank() && ownBank.getId() != pitId;
    }

    public void reset() {
        kalahaBoard.reset();
        playerManager.reset();
        mapper.reset();
    }

    public void changeActivePlayer() {
        playerManager.changeActivePlayer();
    }

    public KalahaBoard getBoard() {
        return kalahaBoard;
    }

    public void setBoard(KalahaBoard kalahaBoard) {
        this.kalahaBoard = kalahaBoard;
    }

    public Player getActivePlayer() {
        return playerManager.getActivePlayer();
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
