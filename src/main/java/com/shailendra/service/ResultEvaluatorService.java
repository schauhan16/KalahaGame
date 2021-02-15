package com.shailendra.service;

import com.shailendra.model.KalahaGameManager;
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
    private KalahaGameManager kalahaGameManager;

    @Autowired
    private PlayerManager playerManager;

    public boolean gameOver() {
        return playerManager.getPlayerList().stream()
                .anyMatch(this::isPitsEmpty);
    }

    private boolean isPitsEmpty(Player player) {
        return kalahaGameManager.getPitsForPlayer(player)
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
        kalahaGameManager.getPitsForPlayer(player)
                .forEach(pit -> {
                    int marbleCount = pit.getNoOfMarbles();
                    pit.setEmpty();
                    kalahaGameManager.depositMarbleInBank(player, marbleCount);
                });

    }

    private int returnResult() {
        int marblesInPlalyer1 = kalahaGameManager.getBoard().getPitById(PITS_FOR_PLAYER).getNoOfMarbles();
        int marblesInPlalyer2 = kalahaGameManager.getBoard().getPitById(TOTAL_NUMBER_OF_PITS).getNoOfMarbles();

        int playerNumber = 0;
        if (marblesInPlalyer1 == marblesInPlalyer2) {
            LOG.info("It's a tie");
        } else if (marblesInPlalyer1 > marblesInPlalyer2) {
            playerNumber = 1;
            LOG.info("Player 1 won");
        } else {
            playerNumber = 2;
            LOG.info("Player 2 won");
        }
        return playerNumber;
    }

    public int getWinner() {
        return returnResult();
    }
}
