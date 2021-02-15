package com.shailendra.service.rules;

import com.shailendra.model.KalahaGameManager;
import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepeatTurnRule {

    @Autowired
    private KalahaGameManager kalahaGameManager;

    public boolean evaluate(Player player, int pitId) {
        return isLastMarbleInPlayerBank(player, pitId);
    }

    private boolean isLastMarbleInPlayerBank(Player player, int pitId) {
        Pit bank = kalahaGameManager.getBankForPlayer(player);
        return bank.getId() == pitId;
    }
}
