package com.shailendra.service;

import com.shailendra.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    @Autowired
    private Board board;

    public void displayBoard() {
        board.display();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
