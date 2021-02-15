package com.shailendra.service.validator;

import com.shailendra.exception.InvalidMoveException;
import com.shailendra.model.Player;
import com.shailendra.service.KalahaGameBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
public class MoveValidatorServiceTest extends KalahaGameBaseTest {

    @Before
    public void setup() {
        super.setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validMove() {
        int pitId = 1;
        Player player = playerManager.getPlayer(1);
        moveValidatorService.validate(player, pitId);
    }

    @Test
    public void playerSelectedOpponentPit() {
        int pitId = 8;
        Player player = playerManager.getPlayer(1);
        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            moveValidatorService.validate(player, pitId);
        });


        String actualMessage = exception.getMessage();
        String expectedMessage = String.format("Pit %d does not belongs to Player %d.", pitId, player.getPlayerNumber());

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void playerSelectedEmptyPit() {
        int pitId = 6;
        kalahaBoard.getPitById(pitId).setEmpty();
        Player player = playerManager.getPlayer(1);

        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            moveValidatorService.validate(player, pitId);
        });

        String expectedMessage = String.format("Pit %d is empty. Please select a non-empty pit.", pitId);
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void playerSelectedBank() {
        int pitId = 7;

        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            moveValidatorService.shouldNotBeABank(pitId);
        });

        String expectedMessage = String.format("Pit %d is a Bank.", pitId);
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

}
