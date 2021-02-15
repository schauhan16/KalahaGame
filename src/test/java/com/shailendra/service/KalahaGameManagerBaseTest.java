package com.shailendra.service;

import com.shailendra.model.KalahaBoard;
import com.shailendra.model.KalahaGameManager;
import com.shailendra.model.PlayerManager;
import com.shailendra.model.PlayerToPitMapper;
import com.shailendra.service.rules.CapturingRule;
import com.shailendra.service.rules.RepeatTurnRule;
import com.shailendra.service.validator.MoveValidatorService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KalahaGameManagerBaseTest {

    @Spy
    @InjectMocks
    protected GameService gameService;

    @Spy
    @InjectMocks
    protected KalahaGamePlay kalahaGamePlay;

    @Spy
    @InjectMocks
    protected KalahaGameManager kalahaGameManager;

    @Spy
    protected KalahaBoard kalahaBoard;

    @Spy
    protected DisplayService displayService;

    @Spy
    @InjectMocks
    protected KalahBoardService boardService;

    @Spy
    @InjectMocks
    protected ResultEvaluatorService resultEvaluatorService;

    @Spy
    @InjectMocks
    protected RepeatTurnRule repeatTurnRule;

    @Spy
    @InjectMocks
    protected CapturingRule capturingRule;

    @Spy
    protected PlayerManager playerManager;

    @Spy
    protected PlayerToPitMapper mapper;

    @Spy
    @InjectMocks
    protected MoveValidatorService moveValidatorService;

    //3, 4, 13, 3, 8, 6, 9, 5, 10, 9, 6, 2, 10, 1, 13, 6, 5, 11, 4, 12, 6, 5, 6, 1, 13
    protected static List<Integer> winningSeqForPlayer1 = Arrays.asList(3, 4, 13, 3, 8, 6, 9, 5, 10, 9, 6, 2, 10, 1, 13, 6, 5, 11, 4, 12, 2, 13);
    protected static List<Integer> winningSeqForPlayer2 = Arrays.asList(1, 10, 13, 3, 9, 13, 12, 1, 13, 11, 5, 13);

    @Before
    public void setup() {
        displayService.setBoard(kalahaBoard);
        kalahaGamePlay.winSeq = new ArrayList<>(winningSeqForPlayer1);

        mapper = new PlayerToPitMapper(kalahaBoard, playerManager);
    }
}
