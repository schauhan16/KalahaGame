package com.shailendra.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.shailendra.config.KalahaGameConfig.NUMBER_OF_PLAYER;

@Component
public class PlayerManager implements Iterable<Player> {

    private final List<Player> playerList = new ArrayList<>();

    private Player activePlayer;

    public PlayerManager() {
        initState();
    }

    private void initState() {
        for (int i = 0; i < NUMBER_OF_PLAYER; i++) {
            playerList.add(new Player(i + 1));
        }

        activePlayer = playerList.get(0);
    }

    public void changeActivePlayer() {
        int index = playerList.indexOf(activePlayer);

        if ((index + 1) == playerList.size()) {
            activePlayer = playerList.get(0);
        } else {
            activePlayer = playerList.get(index + 1);
        }
    }

    public Player getPlayer(int playerNumber) {
        return playerList.get(playerNumber - 1);
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    @Override
    public Iterator<Player> iterator() {
        return playerList.iterator();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void reset() {
        playerList.clear();

        for (int i = 0; i < NUMBER_OF_PLAYER; i++) {
            playerList.add(new Player(i + 1));
        }

        activePlayer = playerList.get(0);
    }
}
