package com.prgmaker.tictactoe;

import com.prgmaker.tictactoe.data.TicTacToeMap;

import java.util.Scanner;

public class TicTacToeHumanPlayer extends Player {
    TicTacToeMap map;
    TicTacToeHumanPlayer(String name, String type, TicTacToeMap map) {
        super(name, type);
        this.map = map;
    }

    @Override
    public boolean decide(int turnId) {
        Scanner sc = new Scanner(System.in);
        Integer cellId = null;
        do {
            try {
                System.out.print("["+getName()+"] Type cell number: ");
                cellId = sc.nextInt();
            } catch (Exception e) {}
        } while(cellId == null);

        return turnId == 0 ? map.setP1Mark(cellId) : map.setP2Mark(cellId);
    }
}
