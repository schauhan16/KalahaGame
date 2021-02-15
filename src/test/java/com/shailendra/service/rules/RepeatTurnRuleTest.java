package com.shailendra.service.rules;

import com.shailendra.model.Player;
import com.shailendra.service.KalahaGameBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class RepeatTurnRuleTest extends KalahaGameBaseTest {

    @Before
    public void setup(){
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRepeatTurnTrue() {
        int lastMarbleInPitId = 7;
        Player player = playerManager.getPlayer(1);
        boolean isRepeatTurn = repeatTurnRule.evaluate(player, lastMarbleInPitId);
        assertTrue(isRepeatTurn);
    }

    @Test
    public void testRepeatTurnFalse() {
        int lastMarbleInPitId = 7;
        Player player = playerManager.getPlayer(2);
        boolean isRepeatTurn = repeatTurnRule.evaluate(player, lastMarbleInPitId);
        assertFalse(isRepeatTurn);
    }
}
