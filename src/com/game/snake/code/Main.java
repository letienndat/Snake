package com.game.snake.code;

public class Main {
    public static void main(String[] args) {
        PanelGame panelGame = new PanelGame();
        while (true) {
            try {
                if (panelGame.isStart()) {
                    panelGame.paintPointDisplay();
                    if (panelGame.isLost() || panelGame.snakeCanChinhMinh() || panelGame.isWin()) {
                        panelGame.startSnake();
                    }
                    Thread.sleep(panelGame.timeSleep());
                }
                if (panelGame.isQuit()) {
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
