package com.shailendra.model;

import com.shailendra.service.KalahaGameBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerToPitMapperTest extends KalahaGameBaseTest {

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReset(){
        Player oldPlayer = playerManager.getPlayer(1);
        gameService.makeMove(4);

        assertEquals(1, mapper.getBankForPlayer(oldPlayer).getNoOfMarbles());
        assertEquals(0, kalahaBoard.getPitById(4).getNoOfMarbles());

        playerManager.reset();
        mapper.reset();

        Player newPlayer = playerManager.getPlayer(1);
        assertNotEquals(newPlayer, oldPlayer);
        assertEquals(newPlayer.getPlayerNumber(), oldPlayer.getPlayerNumber());
    }

    @Test
    public void testGetPitsForPlayer(){
        Player player = playerManager.getPlayer(1);
        List<Pit> pitForPlayer1 = mapper.getPitsForPlayer(player);

        assertTrue(pitForPlayer1.contains(kalahaBoard.getPitById(1)));
        assertFalse(pitForPlayer1.contains(kalahaBoard.getPitById(8)));
    }
}
