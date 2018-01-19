package com.prgmaker.tictactoe.data;

import java.util.Observable;

public class TicTacToeMap extends Observable{
    public static final int ID_NONE = 0;
    public static final int ID_P1 = 1;
    public static final int ID_P2 = 2;
    private int N;

    private int[] map;
    public int getN() {
        return N;
    }

    private int nPick;
    public int getData(int i) {
        return map[i];
    }

    public TicTacToeMap(int n) {
        N = n;
        map = new int[n*n];
        nPick = 0;
    }

    public boolean setP1Mark(int num) {
        if(num > N*N || num <= 0) return false;
        if(map[num-1] != 0) return false;
        map[num-1] = ID_P1;
        nPick++;
        setChanged();
        notifyObservers();
        return true;
    }

    public boolean setP2Mark(int num) {
        if(num > N*N || num <= 0) return false;
        if(map[num-1] != 0) return false;
        map[num-1] = ID_P2;
        nPick++;
        setChanged();
        notifyObservers();
        return true;
    }

    private int rowCrossed() {
        //o.......
        OUTER: for(int i = 0; i < N*N; i++) {
            if(map[i] == ID_NONE) continue;
            for(int j = 1; j < N; j++) {
                if(map[i] != map[j]) continue OUTER;
            }
            return map[i];
        }
        return ID_NONE;
    }

    private int colCrossed() {
        // O O
        // . .
        // . .
        OUTER: for(int i = 0; i < N; i++) {
            if(map[i] == ID_NONE) continue;
            for(int j = i+N; j < N*N; j+=N) {
                if(map[j] != map[i]) continue OUTER;;
            }
            return map[i];
        }
        return ID_NONE;
    }

    private int diagonalCrossed() {
        int i;
        if(map[0] != ID_NONE) {
            for(i = N+1; i < N*N; i+=N+1) {
                if(map[0] != map[i]) break;
            }
            if(i >= N*N) return map[0];
        }

        if(map[N-1] == ID_NONE) return ID_NONE;

        for(i = 2*(N-1); i < N*N-1; i+=N-1) {
            if(map[N-1] != map[i]) return ID_NONE;
        }

        return map[N-1];
    }

    public boolean crossed() {
        return rowCrossed() != ID_NONE || colCrossed() != ID_NONE || diagonalCrossed() != ID_NONE;
    }

    public int getPickNum() {
        return nPick;
    }

    public int whoWin() {
        int winner = rowCrossed();
        if(winner != ID_NONE) return winner;
        winner = colCrossed();
        if(winner != ID_NONE) return winner;
        winner = diagonalCrossed();
        return winner;
    }
}
