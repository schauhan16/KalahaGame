package com.shailendra.service.rules;

import com.shailendra.model.KalahaGame;
import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepeatTurnRule {

    @Autowired
    private KalahaGame kalahaGame;

    public boolean evaluate(Player player, int pitId) {
        return isLastMarbleInPlayerBank(player, pitId);
    }

    protected boolean isLastMarbleInPlayerBank(Player player, int pitId) {
        Pit bank = kalahaGame.getBankForPlayer(player);
        return bank.getId() == pitId;
    }
}
