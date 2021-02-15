package com.shailendra.service;

import com.shailendra.config.KalahaGameConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceIT extends KalahaGameManagerBaseTest {

    @Before
    public void setup(){
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsBank() {
        for (int id = 1; id <= KalahaGameConfig.TOTAL_NUMBER_OF_PITS; id++) {
            if (id % 7 == 0) {
                assertTrue(kalahaBoard.getPitById(id).isBank());
                assertEquals(0, kalahaBoard.getPitById(id).getNoOfMarbles());
            } else {
                assertFalse(kalahaBoard.getPitById(id).isBank());
                assertEquals(KalahaGameConfig.NUMBER_OF_MARBLE_PER_PIT, kalahaBoard.getPitById(id).getNoOfMarbles());
            }
        }
    }

    @Test
    public void testPlayer1Win() {
        for (Integer pitId : winningSeqForPlayer1) {
            when(kalahaGamePlay.selectPitToMove()).thenReturn(pitId);
            kalahaGamePlay.play();

        }
        assertEquals(14, kalahaGameManager.getBankForPlayer(playerManager.getPlayer(2)).getNoOfMarbles());
        assertEquals(25, kalahaGameManager.getBankForPlayer(playerManager.getPlayer(1)).getNoOfMarbles());
        assertTrue(resultEvaluatorService.gameOver());

        resultEvaluatorService.evaluate();
        assertEquals(14, kalahaGameManager.getBankForPlayer(playerManager.getPlayer(2)).getNoOfMarbles());
        assertEquals(34, kalahaGameManager.getBankForPlayer(playerManager.getPlayer(1)).getNoOfMarbles());
    }

    @Test
    public void testStartGame() {
        gameService.startGame();
        assertTrue(resultEvaluatorService.gameOver());
    }

    @Test
    public void testIsLastSeedInOwnEmptyPit() {

    }

    @Test
    public void testIsOwnPit() {
    }

    @Test
    public void testGetOpositePit() {

    }

    @Test
    public void testSkippingOpponentBank() {

    }

    @Test
    public void testGameEnd() {
    }

    @Test
    public void testGameResult() {
    }

    @Test
    public void testCaputringSeeds() {
    }

    @Test
    public void testRepeatTurn() {
    }
}
