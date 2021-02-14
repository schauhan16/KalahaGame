package com.shailendra.service;

import com.shailendra.model.KalahaBoard;
import com.shailendra.model.Pit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.shailendra.config.KalahaGameConfig.TOTAL_NUMBER_OF_PITS;

@Service
public class KalahBoardService {

    @Autowired
    private KalahaBoard kalahaBoard;

    protected int getNextPit(int currentPitId) {
        if (currentPitId == TOTAL_NUMBER_OF_PITS)
            return 1;
        return currentPitId + 1;
    }

    protected int getNoOfMarbles(int pitId) {
        Pit pit = kalahaBoard.getPitById(pitId);
        return pit.getNoOfMarbles();
    }

    protected void emptyPit(int pitId) {
        Pit pit = kalahaBoard.getPitById(pitId);
        pit.setEmpty();
    }

    public boolean wasPitEmpty(int pitId) {
        return getNoOfMarbles(pitId) == 1;
    }

    protected void incrementCount(int pitId) {
        Pit pit = kalahaBoard.getPitById(pitId);
        pit.setNoOfMarbles(pit.getNoOfMarbles() + 1);
    }

    public Pit getOppositePit(int id) {
        //This formula needs to be changed if number of player is more than 2
        int oppositePitId = TOTAL_NUMBER_OF_PITS - id;
        return kalahaBoard.getPitById(oppositePitId);
    }
}
