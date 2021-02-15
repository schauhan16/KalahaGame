package com.shailendra.service;

import com.shailendra.model.Pit;
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
public class KalahBoardServiceTest extends KalahaGameBaseTest{

    @Before
    public void setup(){
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNextPit() {
        assertEquals(2, boardService.getNextPit(1));
        assertEquals(1, boardService.getNextPit(14));
    }

    @Test
    public void testGetNoOfMarbles() {
        assertEquals(4, boardService.getNoOfMarbles(3));
        assertEquals(4, boardService.getNoOfMarbles(4));
        assertEquals(0, boardService.getNoOfMarbles(7));

        kalahaGamePlay.makeMove(3);
        assertEquals(0, boardService.getNoOfMarbles(3));
        assertEquals(5, boardService.getNoOfMarbles(4));
        assertEquals(1, boardService.getNoOfMarbles(7));
    }

    @Test
    public void testEmptyPit() {
        assertEquals(4, boardService.getNoOfMarbles(3));

        boardService.emptyPit(3);
        assertEquals(0, boardService.getNoOfMarbles(3));
    }

    @Test
    public void testWasPitEmpty() {
        Pit pit = kalahaBoard.getPitById(1);

        boardService.emptyPit(1);
        assertTrue(pit.isEmpty());

        boardService.incrementCount(1);
        assertTrue(boardService.wasPitEmpty(1));

        boardService.incrementCount(1);
        assertFalse(boardService.wasPitEmpty(1));
    }

    @Test
    public void testIncrementCount() {
        Pit pit = kalahaBoard.getPitById(1);

        boardService.emptyPit(1);
        assertTrue(pit.isEmpty());

        boardService.incrementCount(1);
        assertFalse(pit.isEmpty());
        assertEquals(1, pit.getNoOfMarbles());
    }

    @Test
    public void testGetOppositePit() {
        assertEquals(13, boardService.getOppositePit(1).getId());
        assertNotEquals(12, boardService.getOppositePit(1).getId());
    }
}
