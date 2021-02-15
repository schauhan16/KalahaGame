package com.shailendra.service;

import com.shailendra.model.GameData;
import com.shailendra.model.GamePlay;
import com.shailendra.model.KalahaGameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    protected ResultEvaluatorService resultEvaluatorService;

    @Autowired
    protected DisplayService displayService;

    @Autowired
    protected GamePlay gamePlay;

    @Autowired
    protected KalahaGameManager kalahaGameManager;

    public void startGame() {
        do {
            gamePlay.play();
        } while (!resultEvaluatorService.gameOver());
        resultEvaluatorService.evaluate();
        displayService.displayBoard();
    }

    public GameData getGameData(){
        GameData data = new GameData(kalahaGameManager.getActivePlayer().getPlayerNumber(), kalahaGameManager.getBoard());
        boolean gameOver = resultEvaluatorService.gameOver();
        data.setGameOver(gameOver);
        if(gameOver){
            data.setWinner(resultEvaluatorService.getWinner());
        }
        return data;
    }

    public GameData makeMove(int pitId) {
        gamePlay.makeMove(pitId);
        if(resultEvaluatorService.gameOver()){
            resultEvaluatorService.evaluate();
        }
        return getGameData();
    }

    public GameData resetGame() {
        gamePlay.restartGame();

        return getGameData();
    }
}
