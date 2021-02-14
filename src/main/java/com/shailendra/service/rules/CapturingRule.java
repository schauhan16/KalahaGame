package com.shailendra.service.rules;

import com.shailendra.model.KalahaGame;
import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import com.shailendra.service.KalahBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapturingRule {

    private static final Logger LOG = LoggerFactory.getLogger(CapturingRule.class);

    @Autowired
    private KalahaGame kalahaGame;

    @Autowired
    private KalahBoardService boardService;

    public void evaluate(Player player, int pitId) {
        if (wasOwnPitEmpty(player, pitId)) {
            Pit oppositePit = boardService.getOppositePit(pitId);
            int marbleCount = oppositePit.getNoOfMarbles();
            if (0 == marbleCount) {
                return;
            }
            LOG.info("Opposite pit id of {} is {}", pitId, oppositePit.getId());
            oppositePit.setEmpty();
            LOG.info("Capturing marbles {}", marbleCount);
            Pit ownPit = kalahaGame.getBoard().getPitById(pitId);
            ownPit.setEmpty();
            kalahaGame.depositMarbleInBank(player, marbleCount + 1);
        }
    }

    private boolean wasOwnPitEmpty(Player player, int id) {
        return kalahaGame.getPitsForPlayer(player)
                .stream()
                .map(Pit::getId)
                .filter(pitId -> pitId == id)
                .anyMatch(boardService::wasPitEmpty);
    }

}
