package com.game.snake.code;

public class ToaDo {
    private int x;
    private int y;

    public ToaDo() {
        x = 0;
        y = 0;
    }

    public ToaDo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToaDo) {
            return ((ToaDo) obj).getX() == this.getX() && ((ToaDo) obj).getY() == this.getY();
        }
        return false;
    }
}
