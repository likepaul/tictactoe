package com.prgmaker.tictactoe;

public abstract class TurnGame {
    protected abstract Player createPlayer(String name, String type);
    protected abstract void registerUser(Player p);
    protected abstract void clearPlayer();
    protected abstract boolean gameOver();
    protected abstract String getResult();
    protected abstract void nextTurn();
    protected abstract void show();
    public final void start(String[][] prop, int n) {
        clearPlayer();
        for(int i = 0; i < n; i++) {
            registerUser(createPlayer(prop[i][0], prop[i][1]));
        }

        Player p;
        show();
        while(!gameOver()) {
            nextTurn();
        }

        System.out.println(getResult());
    }
}
