package com.shailendra.service;

import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
public class ResultEvaluatorServiceTest extends KalahaGameManagerBaseTest {

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
}
