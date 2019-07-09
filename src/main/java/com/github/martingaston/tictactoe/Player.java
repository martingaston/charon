package com.github.martingaston.tictactoe;

public interface Player {
    Symbol symbol();

    int getNextMove(Board board);
}