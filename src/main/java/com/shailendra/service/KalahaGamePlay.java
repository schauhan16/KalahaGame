package com.shailendra.service;

import com.shailendra.model.GamePlay;
import com.shailendra.model.KalahaGame;
import com.shailendra.model.Player;
import com.shailendra.service.rules.CapturingRule;
import com.shailendra.service.rules.RepeatTurnRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KalahaGamePlay implements GamePlay {
    Logger LOG = LoggerFactory.getLogger(KalahaGamePlay.class);

    @Autowired
    protected KalahaGame kalahaGame;

    @Autowired
    protected DisplayService displayService;

    @Autowired
    protected KalahBoardService boardService;

    @Autowired
    protected RepeatTurnRule repeatTurnRule;

    @Autowired
    protected CapturingRule capturingRule;

    @Autowired
    protected MoveValidatorService moveValidatorService;

    public void play() {
        int pitId = selectPitToMove();
        makeMove(pitId);
    }

    @Override
    public void makeMove(int pitId) {
        Player player = kalahaGame.getActivePlayer();
        LOG.info("Player {} selected for pit: {}", player.getPlayerNumber(), pitId);

        moveValidatorService.validate(player, pitId);

        int marbleCount = boardService.getNoOfMarbles(pitId); // should not be empty pit
        LOG.info("Marble count in pit: {} is: {}", pitId, marbleCount);
        boardService.emptyPit(pitId);
        int lastPitId = executeTurn(player, pitId, marbleCount);

        LOG.info("Last marble in pit: {}", lastPitId);
        capturingRule.evaluate(player, lastPitId);

        if (!repeatTurnRule.evaluate(player, lastPitId)) {
            LOG.info("Changing turn!!!");
            kalahaGame.changeActivePlayer();
        }
        displayService.displayBoard();
    }

    @Override
    public void restartGame() {
        kalahaGame.reset();
    }

    protected int executeTurn(Player player, int pitId, int marbleCount) {
        int currentPitId = pitId;
        while (marbleCount > 0) {
            int nextPitId = boardService.getNextPit(currentPitId);
            if (!kalahaGame.isOpponentBank(player, nextPitId)) {
                boardService.incrementCount(nextPitId);
                marbleCount--;
            }
            currentPitId = nextPitId;
        }
        return currentPitId;
    }


    protected List<Integer> winSeq = new ArrayList<>();

    protected int selectPitToMove() {
        return winSeq.remove(0);
    }
}
