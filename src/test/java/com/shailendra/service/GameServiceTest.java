package com.shailendra.service;

import com.shailendra.model.GameData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest extends KalahaGameBaseTest{
    @Before
    public void setup(){
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGameData() {
        GameData data = gameService.makeMove(4);

        assertEquals(2, data.getActivePlayer());
        assertFalse(data.isGameOver());
        assertEquals(0, data.getWinner());

    }

    @Test
    public void testMakeMove() {
        GameData data = gameService.makeMove(3);

        assertEquals(1, data.getActivePlayer());
        assertFalse(data.isGameOver());
        assertEquals(0, data.getWinner());
    }

    @Test
    public void testResetGame() {
        for (Integer pitId : winningSeqForPlayer1) {
            gameService.makeMove(pitId);
        }

        GameData data = gameService.getGameData();
        assertTrue(data.isGameOver());
        assertEquals(1, data.getWinner());

        data = gameService.resetGame();
        assertEquals(1, data.getActivePlayer());
        assertFalse(data.isGameOver());
        assertEquals(0, data.getWinner());
    }
}
