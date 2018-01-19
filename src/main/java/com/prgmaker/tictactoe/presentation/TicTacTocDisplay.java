package com.prgmaker.tictactoe.presentation;

import com.prgmaker.tictactoe.data.TicTacToeMap;

import java.util.Observable;
import java.util.Observer;

import static com.prgmaker.tictactoe.data.TicTacToeMap.ID_NONE;
import static com.prgmaker.tictactoe.data.TicTacToeMap.ID_P1;

public class TicTacTocDisplay implements Observer {
    TicTacToeMap map;
    private final char P1_MARK = '#';
    private final char P2_MARK = 'X';
    public TicTacTocDisplay(TicTacToeMap map) {
        this.map = map;
        map.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        show();
    }

    public void show() {
        int N = map.getN();
        int digit = (int)(Math.log(N*N)/Math.log(10))+1;
        int i = 0;
        int cellData;
        for(int row = 0; row < 2*N+1; row++) {
            for(int col = 0; col < 2*N+1; col++) {
                if((row&1)!=0 && (col&1)!=0) {
                    cellData = map.getData(i++);
                    System.out.print(" " + (cellData == ID_NONE ? String.format("%0"+digit+"d", i) :
                        String.format("%"+digit+"s", cellData == ID_P1 ? P1_MARK: P2_MARK)) + " ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}
