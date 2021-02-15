package com.shailendra.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
public class KalahaGameManagerPlayTest extends KalahaGameManagerBaseTest {

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
}
