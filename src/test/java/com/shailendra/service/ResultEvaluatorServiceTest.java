package com.shailendra.service;

import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
public class ResultEvaluatorServiceTest extends KalahaGameBaseTest {

    @Before
    public void setup(){
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGameOver(){
        Player player1 = playerManager.getPlayer(1);
        mapper.getPitsForPlayer(player1)
                .forEach(Pit::setEmpty);

        assertTrue(resultEvaluatorService.gameOver());
    }

    @Test
    public void testGameResult() {
        Player player1 = playerManager.getPlayer(1);
        mapper.getBankForPlayer(player1).setNoOfMarbles(10);

        Player player2 = playerManager.getPlayer(2);
        mapper.getBankForPlayer(player2).setNoOfMarbles(5);

        assertFalse(resultEvaluatorService.gameOver());

        mapper.getPitsForPlayer(player1)
                .forEach(Pit::setEmpty);

        assertTrue(resultEvaluatorService.gameOver());
        assertEquals(1, resultEvaluatorService.getWinner());
    }

    @Test
    public void testGameResultAsTie() {
        Player player1 = playerManager.getPlayer(1);
        mapper.getBankForPlayer(player1).setNoOfMarbles(5);

        Player player2 = playerManager.getPlayer(2);
        mapper.getBankForPlayer(player2).setNoOfMarbles(5);

        assertFalse(resultEvaluatorService.gameOver());

        mapper.getPitsForPlayer(player1)
                .forEach(Pit::setEmpty);

        assertTrue(resultEvaluatorService.gameOver());
        assertEquals(0, resultEvaluatorService.getWinner());
    }

    @Test
    public void testMovingRemainingMarblesToPlayerBank() {
        Player player1 = playerManager.getPlayer(1);
        mapper.getBankForPlayer(player1).setNoOfMarbles(10);

        Player player2 = playerManager.getPlayer(2);
        mapper.getBankForPlayer(player2).setNoOfMarbles(5);

        assertFalse(resultEvaluatorService.gameOver());

        mapper.getPitsForPlayer(player1)
                .forEach(Pit::setEmpty);

        resultEvaluatorService.evaluate();
        assertTrue(resultEvaluatorService.gameOver());
        assertEquals(2, resultEvaluatorService.getWinner());
        assertTrue(mapper.getPitsForPlayer(player2).stream().allMatch(Pit::isEmpty));
        assertEquals(29, mapper.getBankForPlayer(player2).getNoOfMarbles());
    }

}
