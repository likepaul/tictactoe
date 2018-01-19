package com.prgmaker.tictactoe;

public abstract class Player {
    public abstract boolean decide(int turnId);
    private final String name, type;

    protected Player(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
