package com.shailendra.service;

import com.shailendra.model.Pit;
import com.shailendra.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
public class KalahaGamePlayTest extends KalahaGameBaseTest {

    @Before
    public void setup(){
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIncrementInOwnBank() {
        doReturn(4).when(kalahaGamePlay).selectPitToMove();
        kalahaGamePlay.play();

        assertEquals(playerManager.getPlayer(2), kalahaGameManager.getActivePlayer());
        assertEquals(0, kalahaBoard.getPitById(4).getNoOfMarbles());
        assertEquals(5, kalahaBoard.getPitById(5).getNoOfMarbles());
        assertEquals(5, kalahaBoard.getPitById(6).getNoOfMarbles());
        assertEquals(1, kalahaBoard.getPitById(7).getNoOfMarbles()); // It's a bank. Should have only one
        assertEquals(5, kalahaBoard.getPitById(8).getNoOfMarbles());
    }

    @Test
    public void testSkippingOpponentBank() {
        Pit pit = kalahaBoard.getPitById(6);
        pit.setNoOfMarbles(10);

        Player activePlayer = kalahaGameManager.getActivePlayer();

        assertEquals(1, activePlayer.getPlayerNumber());
        assertEquals(0, mapper.getBankForPlayer(activePlayer).getNoOfMarbles());
        assertEquals(0, mapper.getBankForPlayer(playerManager.getPlayer(2)).getNoOfMarbles());

        doReturn(6).when(kalahaGamePlay).selectPitToMove();
        kalahaGamePlay.play();

        assertEquals(2, kalahaGameManager.getActivePlayer().getPlayerNumber());
        assertEquals(1, mapper.getBankForPlayer(activePlayer).getNoOfMarbles());
        assertEquals(5, kalahaBoard.getPitById(13).getNoOfMarbles());
        assertEquals(5, kalahaBoard.getPitById(1).getNoOfMarbles());
        assertEquals(0, mapper.getBankForPlayer(playerManager.getPlayer(2)).getNoOfMarbles());
    }
}
