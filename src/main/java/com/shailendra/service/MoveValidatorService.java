package com.shailendra.service;

import com.shailendra.exception.InvalidMoveException;
import com.shailendra.model.KalahaGame;
import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import com.shailendra.model.PlayerToPitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveValidatorService {

    @Autowired
    private PlayerToPitMapper playerToPitMapper;

    @Autowired
    private KalahaGame kalahaGame;

    public void validate(Player player, int pitId) {
        pitBelongsToPlayer(player, pitId);
        pitIsNonEmpty(pitId);
    }

    private void pitIsNonEmpty(int pitId) {
        Pit pit = kalahaGame.getBoard().getPitById(pitId);
        if(pit.isEmpty()){
            throw new InvalidMoveException(String.format("Pit %d is empty. Please select a non-empty pit", pitId));
        }
    }

    private void pitBelongsToPlayer(Player player, int pitId) {
        boolean isPlayerPit = playerToPitMapper.getPitsForPlayer(player)
                .stream()
                .anyMatch(pit -> pitId == pit.getId());

        if(!isPlayerPit){
            throw new InvalidMoveException(String.format("Pit %d does not belongs to Player %d", pitId, player.getPlayerNumber()));
        }
    }
}
