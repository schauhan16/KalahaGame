package com.shailendra.service.rules;

import com.shailendra.model.Player;
import com.shailendra.service.KalahaGameBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CapturingRuleTest extends KalahaGameBaseTest {

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void playerCapturedMarbles(){
        int lastMarbleInPitId = 6;
        Player player = playerManager.getPlayer(1);

        mapper.getBankForPlayer(player).setNoOfMarbles(5);
        kalahaBoard.getPitById(lastMarbleInPitId).setNoOfMarbles(1);

        assertEquals(4, boardService.getOppositePit(lastMarbleInPitId).getNoOfMarbles());

        capturingRule.evaluate(player, lastMarbleInPitId);

        assertEquals(0, boardService.getOppositePit(lastMarbleInPitId).getNoOfMarbles());
        assertEquals(0, boardService.getNoOfMarbles(lastMarbleInPitId));
        assertEquals(10, mapper.getBankForPlayer(player).getNoOfMarbles());
    }

    @Test
    public void playerNotCapturedMarbles(){
        int lastMarbleInPitId = 6;
        Player player = playerManager.getPlayer(1);

        mapper.getBankForPlayer(player).setNoOfMarbles(5);
        kalahaBoard.getPitById(lastMarbleInPitId).setNoOfMarbles(2);

        assertEquals(4, boardService.getOppositePit(lastMarbleInPitId).getNoOfMarbles());

        capturingRule.evaluate(player, lastMarbleInPitId);

        assertEquals(4, boardService.getOppositePit(lastMarbleInPitId).getNoOfMarbles());
        assertEquals(2, boardService.getNoOfMarbles(lastMarbleInPitId));
        assertEquals(5, mapper.getBankForPlayer(player).getNoOfMarbles());
    }
}
