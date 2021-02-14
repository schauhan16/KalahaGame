package com.shailendra.service;

import com.shailendra.model.KalahaGame;
import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import com.shailendra.model.PlayerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.shailendra.config.KalahaGameConfig.PITS_FOR_PLAYER;
import static com.shailendra.config.KalahaGameConfig.TOTAL_NUMBER_OF_PITS;

@Service
public class ResultEvaluatorService {

    private static final Logger LOG = LoggerFactory.getLogger(ResultEvaluatorService.class);

    @Autowired
    private KalahaGame kalahaGame;

    @Autowired
    private PlayerManager playerManager;

    public boolean gameOver() {
        return playerManager.getPlayerList().stream()
                .anyMatch(this::isPitsEmpty);
    }

    private boolean isPitsEmpty(Player player) {
        return kalahaGame.getPitsForPlayer(player)
                .stream()
                .allMatch(Pit::isEmpty);
    }

    public void evaluate() {
        Player player = playerManager.getPlayerList()
                .stream()
                .filter(p -> !this.isPitsEmpty(p))
                .findFirst()
                .get();

        moveRemainingMarbleToPlayerBank(player);
        returnResult();
    }

    private void moveRemainingMarbleToPlayerBank(Player player) {
        LOG.info("Emptying pit for player {}", player.getPlayerNumber());
        kalahaGame.getPitsForPlayer(player)
                .forEach(pit -> {
                    int marbleCount = pit.getNoOfMarbles();
                    pit.setEmpty();
                    kalahaGame.depositMarbleInBank(player, marbleCount);
                });

    }

    private int returnResult() {
        int marblesInPlalyer1 = kalahaGame.getBoard().getPitById(PITS_FOR_PLAYER).getNoOfMarbles();
        int marblesInPlalyer2 = kalahaGame.getBoard().getPitById(TOTAL_NUMBER_OF_PITS).getNoOfMarbles();

        int playerNumber = 0;
        if (marblesInPlalyer1 == marblesInPlalyer2) {
            System.out.println("It's a tie");
        } else if (marblesInPlalyer1 > marblesInPlalyer2) {
            playerNumber = 1;
            System.out.println("Player 1 won");
        } else {
            playerNumber = 2;
            System.out.println("Player 2 won");
        }
        return playerNumber;
    }

    public int getWinner() {
        return returnResult();
    }
}
