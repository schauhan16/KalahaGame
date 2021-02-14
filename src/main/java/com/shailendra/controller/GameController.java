package com.shailendra.controller;

import com.shailendra.model.GameData;
import com.shailendra.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public GameData getGameData() {
        return gameService.getGameData();
    }

    @GetMapping("/move/{pitId}")
    public GameData makeTurn(@PathVariable Integer pitId) {
        return gameService.makeMove(pitId);
    }

    @GetMapping("/reset")
    public GameData resetGame(){
        return gameService.resetGame();
    }
}
