package com.prgmaker.tictactoe;

import com.prgmaker.tictactoe.data.TicTacToeMap;
import com.prgmaker.tictactoe.presentation.TicTacTocDisplay;

import java.util.ArrayList;

public class TicTacToe extends TurnGame {
    private ArrayList<Player> players;
    private int turnId = 0;
    private TicTacToeMap map;
    private final TicTacTocDisplay display;

    public TicTacToe() {
        players = new ArrayList<>();
        map = new TicTacToeMap(4);
        display = new TicTacTocDisplay(map);
    }

    protected Player createPlayer(String name, String type) {
        if(type.equals("human")) {
            return new TicTacToeHumanPlayer(name, type, map);
        } else if(type.equals("computer")) {
            return new TicTacToeHumanPlayer(name, type, map);
        }
        throw new IllegalArgumentException("Not support player " + type);
    }

    protected void registerUser(Player p) {
        int size = players.size();
        if(size == 2) {
            throw new IllegalStateException("You can not register more players than 2.");
        }
        players.add(p);
    }

    protected void clearPlayer() {
        players.clear();
    }

    protected boolean gameOver() {
        int size = map.getN();
        return map.crossed() || size*size == map.getPickNum();
    }

    protected String getResult() {
        int winner = map.whoWin();
        if(winner == TicTacToeMap.ID_NONE) {
            return "End in a draw.";
        }
        return (winner == TicTacToeMap.ID_P1 ? players.get(0).getName() : players.get(1).getName()) + " won";
    }

    @Override
    protected void nextTurn() {
        if(turnId == players.size()) {
            turnId = 0;
        }

        if(players.get(turnId).decide(turnId)) {
            turnId++;
        }
    }

    @Override
    protected void show() {
        display.show();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        game.start(new String[][]{{"player1", "human"}, {"player2", "human"}},2);
    }
}
