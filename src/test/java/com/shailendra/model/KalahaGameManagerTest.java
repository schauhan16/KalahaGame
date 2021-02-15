package com.shailendra.model;

import com.shailendra.service.KalahaGameBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class KalahaGameManagerTest extends KalahaGameBaseTest {

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDepositMarbleInBank() {
        Player player = kalahaGameManager.getActivePlayer();
        Pit pit = kalahaGameManager.getBankForPlayer(player);

        assertEquals(0, pit.getNoOfMarbles());
        kalahaGameManager.depositMarbleInBank(player , 5);

        assertEquals(5, pit.getNoOfMarbles());
    }

    @Test
    public void testIsOpponentBank() {
        Player player = kalahaGameManager.getActivePlayer();

        assertEquals(1, player.getPlayerNumber());

        assertTrue(kalahaGameManager.isOpponentBank(player, 14));
        assertFalse(kalahaGameManager.isOpponentBank(player, 7));
    }

    @Test
    public void testReset() {
        kalahaGamePlay.makeMove(4);

        Player player = playerManager.getPlayer(1);
        Pit bankForPlayer = kalahaGameManager.getBankForPlayer(player);
        assertEquals(1, bankForPlayer.getNoOfMarbles());
        assertEquals(2, kalahaGameManager.getActivePlayer().getPlayerNumber());
        assertEquals(0, kalahaBoard.getPitById(4).getNoOfMarbles());

        kalahaGameManager.reset();

        player = playerManager.getPlayer(1);
        bankForPlayer = kalahaGameManager.getBankForPlayer(player);
        assertEquals(0, bankForPlayer.getNoOfMarbles());
        assertEquals(1, kalahaGameManager.getActivePlayer().getPlayerNumber());
        assertEquals(4, kalahaBoard.getPitById(4).getNoOfMarbles());

    }

    @Test
    public void testChangeActivePlayer() {
        Player player = playerManager.getPlayer(1);
        playerManager.setActivePlayer(player);

        assertEquals(player, kalahaGameManager.getActivePlayer());
        kalahaGameManager.changeActivePlayer();

        assertNotEquals(player, kalahaGameManager.getActivePlayer());
        assertEquals(2, kalahaGameManager.getActivePlayer().getPlayerNumber());
    }
}
