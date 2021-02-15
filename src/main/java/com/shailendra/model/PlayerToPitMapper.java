package com.shailendra.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shailendra.config.KalahaGameConfig.NUMBER_OF_PLAYER;
import static com.shailendra.config.KalahaGameConfig.PITS_FOR_PLAYER;

public class PlayerToPitMapper {

    private final Map<Player, List<Pit>> map = new HashMap<>();

    private KalahaBoard kalahaBoard;

    private PlayerManager playerManager;

    public PlayerToPitMapper() {

    }

    public PlayerToPitMapper(KalahaBoard board, PlayerManager playerManager) {
        this.kalahaBoard = board;
        this.playerManager = playerManager;

        createMap();
    }

    private void createMap() {
        for (int i = 1; i <= NUMBER_OF_PLAYER; i++) {
            map.put(playerManager.getPlayer(i), initPitsForPlayer(i));
        }
    }

    private List<Pit> initPitsForPlayer(int playerId) {
        int startPitId = (playerId - 1) * PITS_FOR_PLAYER;
        int endPitId = playerId * PITS_FOR_PLAYER;
        List<Pit> pits = new ArrayList<>();
        for (int i = startPitId + 1; i <= endPitId; i++) {
            pits.add(kalahaBoard.getPitById(i));
        }

        return pits;
    }

    public List<Pit> getPitsForPlayer(Player player) {
        List<Pit> pitsForPlayer = map.get(player);
        return pitsForPlayer.subList(0, PITS_FOR_PLAYER - 1); // All pits except bank
    }

    public Pit getBankForPlayer(Player player) {
        return map.get(player).get(PITS_FOR_PLAYER - 1);
    }

    public void reset() {
        map.clear();
        createMap();
    }

    public KalahaBoard getKalahaBoard() {
        return kalahaBoard;
    }

    public void setKalahaBoard(KalahaBoard kalahaBoard) {
        this.kalahaBoard = kalahaBoard;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

}
