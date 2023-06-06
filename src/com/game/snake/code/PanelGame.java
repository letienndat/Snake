package com.game.snake.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class PanelGame extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private static final int STEPWIDTH = 20;
    private static final int STEPHEIGHT = 20;
    private int startX = 1;
    private int startY = 1;
    private JLayeredPane layeredPaneGameStart;
    private JLabel labelTitleStart;
    private JButton buttonPlay;
    private JButton buttonRemuse;
    private JButton buttonQuit;
    private JLabel labelCopyrightStart;
    private JLayeredPane layeredPaneGamePlay;
    private JLayeredPane layeredPaneGameOther;
    private JLabel labelOtherTitle;
    private JLayeredPane layeredPaneHuongDanGameOther;
    private JLabel labelOtherKeybroad;
    private JLabel labelOtherKeyW;
    private JLabel labelOtherKeyD;
    private JLabel labelOtherKeyS;
    private JLabel labelOtherKeyA;
    private JLabel labelOtherDieuHuong;
    private JLabel labelOtherKeyESC;
    private JLabel labelOtherKeySPACE;
    private JLayeredPane layeredPaneScoreOther;
    private JLabel labelOtherScore1;
    private JLabel labelOtherScore2;
    private JLayeredPane layeredPaneHighScoreOther;
    private JLabel labelOtherHighScore1;
    private JLabel labelOtherHighScore2;
    private JLayeredPane layeredPaneEffectKeybroad;
    private JLabel labelEffect;
    private JLabel labelEffectW;
    private JLabel labelEffectA;
    private JLabel labelEffectS;
    private JLabel labelEffectD;
    private JLabel labelEffectSPACE;
    private JLabel labelCopyrightPlay;
    private LinkedList<ToaDo> linkedListToaDo;
    private ToaDo toaDoMoi;
    private JLayeredPane[][] panelPoint;
    private LinkedList<Integer> phuongHuong;
    private boolean lost = false;
    private static final String sourceImage = "/com/game/snake/image/";
    private static final String sourceScore = "/com/game/snake/score/";
    private boolean quit = false;
    private boolean start = false;
    private int temp = -1;

    public PanelGame() {
        super("Snake [letienndat]");
        setSize(WIDTH - 5 + 200, HEIGHT - 1);
        setIconImage(new ImageIcon(PanelGame.class.getResource(sourceImage + "snake_title.png")).getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
        init();
        initStart();
        setLayout(null);
    }

    public void init() {
        layeredPaneGamePlay = new JLayeredPane();
        layeredPaneGamePlay.setOpaque(true);
        layeredPaneGamePlay.setBackground(new Color(0, 0, 0));
        layeredPaneGamePlay.setBounds(0, 0, this.getWidth(), this.getHeight());

        panelPoint = new JLayeredPane[(WIDTH / STEPWIDTH) - 2][(HEIGHT / STEPHEIGHT) - 1];

        for (int i = 0; i < panelPoint.length; i++) {
            for (int i1 = 0; i1 < panelPoint[i].length; i1++) {
                panelPoint[i][i1] = new JLayeredPane();
                panelPoint[i][i1].setOpaque(true);
                panelPoint[i][i1].setBounds(startX, startY, STEPWIDTH - 2, STEPHEIGHT - 2);
                panelPoint[i][i1].setBackground(new Color(0, 0, 0));
                panelPoint[i][i1].setLayout(null);
                layeredPaneGamePlay.add(panelPoint[i][i1], 0);
                startX += STEPWIDTH;
            }
            startX = 1;
            startY += STEPHEIGHT;
        }

        layeredPaneGameOther = new JLayeredPane();
        layeredPaneGameOther.setOpaque(true);
        layeredPaneGameOther.setBounds(WIDTH - STEPWIDTH, 0, this.getWidth() - WIDTH + 5, this.getHeight());
        layeredPaneGameOther.setBackground(new Color(157, 193, 193));

        labelOtherTitle = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "snake_title_200x100.png")), JLabel.CENTER);
        labelOtherTitle.setBounds(5, 35, 200, 100);
        labelOtherTitle.setFont(new Font("Raphtalia", Font.PLAIN, 30));
        labelOtherTitle.setForeground(new Color(255, 255, 255));
        layeredPaneGameOther.add(labelOtherTitle);

        layeredPaneHuongDanGameOther = new JLayeredPane();
        layeredPaneHuongDanGameOther.setOpaque(true);
        layeredPaneHuongDanGameOther.setBounds(20, 170, 160, 145);
        layeredPaneHuongDanGameOther.setBackground(new Color(212, 168, 141));

        labelOtherKeybroad = new JLabel("KEYBROAD");
        labelOtherKeybroad.setBounds(3, 5, 100, 10);
        labelOtherKeybroad.setForeground(new Color(95, 95, 95));
        labelOtherKeybroad.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
        layeredPaneHuongDanGameOther.add(labelOtherKeybroad, 0);

        labelOtherKeyW = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_w.png")), JLabel.CENTER);
        labelOtherKeyW.setBounds(160 / 2 - 25 / 2, 15, 25, 25);
        labelOtherKeyW.setForeground(new Color(255, 255, 255));
        layeredPaneHuongDanGameOther.add(labelOtherKeyW, 0);

        labelOtherKeyA = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_a.png")), JLabel.CENTER);
        labelOtherKeyA.setBounds(labelOtherKeyW.getX() - 20, 35, 25, 25);
        labelOtherKeyA.setForeground(new Color(255, 255, 255));
        layeredPaneHuongDanGameOther.add(labelOtherKeyA, 0);

        labelOtherKeyS = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_s.png")), JLabel.CENTER);
        labelOtherKeyS.setBounds(labelOtherKeyW.getX(), 35, 25, 25);
        labelOtherKeyS.setForeground(new Color(255, 255, 255));
        layeredPaneHuongDanGameOther.add(labelOtherKeyS, 0);

        labelOtherKeyD = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_d.png")), JLabel.CENTER);
        labelOtherKeyD.setBounds(labelOtherKeyW.getX() + 20, 35, 25, 25);
        labelOtherKeyD.setForeground(new Color(255, 255, 255));
        layeredPaneHuongDanGameOther.add(labelOtherKeyD, 0);

        labelOtherDieuHuong = new JLabel("UP/DOWN/LEFT/RIGHT");
        labelOtherDieuHuong.setForeground(new Color(255, 255, 255));
        labelOtherDieuHuong.setBounds(20, 58, 130, 20);
        labelOtherDieuHuong.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        layeredPaneHuongDanGameOther.add(labelOtherDieuHuong);

        labelOtherKeyESC = new JLabel("MENU", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_esc.png")), JLabel.CENTER);
        labelOtherKeyESC.setBounds(50, 90, 25 + 36, 25);
        labelOtherKeyESC.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        labelOtherKeyESC.setForeground(new Color(255, 255, 255));
        layeredPaneHuongDanGameOther.add(labelOtherKeyESC, 0);

        labelOtherKeySPACE = new JLabel("PAUSE", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_space.png")), JLabel.CENTER);
        labelOtherKeySPACE.setBounds(50, 120, 25 + 44, 25);
        labelOtherKeySPACE.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        labelOtherKeySPACE.setForeground(new Color(255, 255, 255));
        layeredPaneHuongDanGameOther.add(labelOtherKeySPACE, 0);

        layeredPaneGameOther.add(layeredPaneHuongDanGameOther, 0);

        layeredPaneScoreOther = new JLayeredPane();
        layeredPaneScoreOther.setOpaque(true);
        layeredPaneScoreOther.setBackground(new Color(212, 168, 141));
        layeredPaneScoreOther.setBounds(20, 355, 70, 50);

        labelOtherScore1 = new JLabel("SCORE");
        labelOtherScore1.setBounds(3, 5, 40, 10);
        labelOtherScore1.setForeground(new Color(95, 95, 95));
        labelOtherScore1.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
        layeredPaneScoreOther.add(labelOtherScore1, 0);

        labelOtherScore2 = new JLabel("0", JLabel.CENTER);
        labelOtherScore2.setBounds(0, 22, layeredPaneScoreOther.getWidth(), 20);
        labelOtherScore2.setForeground(new Color(255, 255, 255));
        labelOtherScore2.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        layeredPaneScoreOther.add(labelOtherScore2, 0);

        layeredPaneGameOther.add(layeredPaneScoreOther, 0);

        layeredPaneHighScoreOther = new JLayeredPane();
        layeredPaneHighScoreOther.setOpaque(true);
        layeredPaneHighScoreOther.setBackground(new Color(212, 168, 141));
        layeredPaneHighScoreOther.setBounds(110, 355, 70, 50);

        labelOtherHighScore1 = new JLabel("HIGH");
        labelOtherHighScore1.setBounds(3, 5, 40, 10);
        labelOtherHighScore1.setForeground(new Color(95, 95, 95));
        labelOtherHighScore1.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
        layeredPaneHighScoreOther.add(labelOtherHighScore1, 0);

        labelOtherHighScore2 = new JLabel("", JLabel.CENTER);
        labelOtherHighScore2.setBounds(0, 22, layeredPaneHighScoreOther.getWidth(), 20);
        labelOtherHighScore2.setForeground(new Color(255, 255, 255));
        labelOtherHighScore2.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        layeredPaneHighScoreOther.add(labelOtherHighScore2, 0);

        layeredPaneGameOther.add(layeredPaneHighScoreOther, 0);

        layeredPaneEffectKeybroad = new JLayeredPane();
        layeredPaneEffectKeybroad.setOpaque(true);
        layeredPaneEffectKeybroad.setBackground(new Color(212, 168, 141));
        layeredPaneEffectKeybroad.setBounds(20, 450, 160, 120);

        labelEffect = new JLabel("<HTML>KEYBROAD<br/>EFFECT</HTML>");
        labelEffect.setBounds(5, 3, 60, 30);
        labelEffect.setForeground(new Color(95, 95, 95));
        labelEffect.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
        layeredPaneEffectKeybroad.add(labelEffect, 0);

        labelEffectW = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_w_pressed.png")), JLabel.CENTER);
        labelEffectW.setBounds(layeredPaneEffectKeybroad.getWidth() / 2 - 37, 40, 30, 30);
        layeredPaneEffectKeybroad.add(labelEffectW, 0);

        labelEffectS = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_s_pressed.png")), JLabel.CENTER);
        labelEffectS.setBounds(layeredPaneEffectKeybroad.getWidth() / 2 - 30 / 2, 70, 30, 30);
        layeredPaneEffectKeybroad.add(labelEffectS, 0);

        labelEffectA = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_a_pressed.png")), JLabel.CENTER);
        labelEffectA.setBounds(labelEffectS.getX() - 30, 70, 30, 30);
        layeredPaneEffectKeybroad.add(labelEffectA, 0);

        labelEffectD = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_d_pressed.png")), JLabel.CENTER);
        labelEffectD.setBounds(labelEffectS.getX() + 30, 70, 30, 30);
        layeredPaneEffectKeybroad.add(labelEffectD, 0);

        labelEffectSPACE = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "key_space_pressed.png")), JLabel.CENTER);
        labelEffectSPACE.setBounds(labelEffectW.getX() + 35, 43, 30, 30);
        layeredPaneEffectKeybroad.add(labelEffectSPACE, 0);

        layeredPaneGameOther.add(layeredPaneEffectKeybroad, 0);

        labelCopyrightPlay = new JLabel("Copyright of letienndat");
        labelCopyrightPlay.setForeground(new Color(0, 0, 0));
        labelCopyrightPlay.setFont(new Font("Inter", Font.PLAIN, 10));
        labelCopyrightPlay.setBounds(0, layeredPaneGameOther.getHeight() - 90, layeredPaneGameOther.getWidth(), 13);
        labelCopyrightPlay.setHorizontalAlignment(JLabel.CENTER);
        layeredPaneGameOther.add(labelCopyrightPlay, 0);

        layeredPaneGamePlay.add(layeredPaneGameOther, 0);

        add(layeredPaneGamePlay);
        layeredPaneGamePlay.setVisible(false);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() - 32 == KeyEvent.VK_W) {
                    labelEffectW.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_w_released.png")));
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_A) {
                    labelEffectA.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_a_released.png")));
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_S) {
                    labelEffectS.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_s_released.png")));
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_D) {
                    labelEffectD.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_d_released.png")));
                } else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
                    labelEffectSPACE.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_space_released.png")));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() - 32 == KeyEvent.VK_W) {
                    labelEffectW.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_w_pressed.png")));
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_A) {
                    labelEffectA.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_a_pressed.png")));
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_S) {
                    labelEffectS.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_s_pressed.png")));
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_D) {
                    labelEffectD.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_d_pressed.png")));
                } else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
                    labelEffectSPACE.setIcon(new ImageIcon(PanelGame.class.getResource(sourceImage + "key_space_pressed.png")));
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() - 32 == KeyEvent.VK_W && phuongHuong.getLast() != 3 && phuongHuong.getLast() != 1 && temp != 3 && temp != -1) {
                    temp = 1;
                    if (phuongHuong.getLast() == 0) {
                        phuongHuong.removeLast();
                    }
                    phuongHuong.addLast(1);
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_D && phuongHuong.getLast() != 4 && phuongHuong.getLast() != 2 && temp != 4) {
                    temp = 2;
                    if (phuongHuong.getLast() == 0) {
                        phuongHuong.removeLast();
                    }
                    phuongHuong.addLast(2);
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_S && phuongHuong.getLast() != 1 && phuongHuong.getLast() != 3 && temp != 1) {
                    temp = 3;
                    if (phuongHuong.getLast() == 0) {
                        phuongHuong.removeLast();
                    }
                    phuongHuong.addLast(3);
                } else if (e.getKeyChar() - 32 == KeyEvent.VK_A && phuongHuong.getLast() != 2 && phuongHuong.getLast() != 4 && temp != 2 && temp != -1) {
                    temp = 4;
                    if (phuongHuong.getLast() == 0) {
                        phuongHuong.removeLast();
                    }
                    phuongHuong.addLast(4);
                } else if (e.getKeyChar() == KeyEvent.VK_SPACE && phuongHuong.getLast() != 0) {
                    temp = phuongHuong.getLast();
                    phuongHuong.addLast(0);
                } else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    start = false;
                    buttonRemuse.setVisible(true);
                    buttonPlay.setBounds(buttonPlay.getX(), 350, buttonPlay.getWidth(), buttonRemuse.getHeight());
                    buttonQuit.setBounds(buttonQuit.getX(), 400, buttonQuit.getWidth(), buttonQuit.getHeight());
                    layeredPaneGamePlay.setVisible(false);
                    layeredPaneGameStart.setVisible(true);
                }
            }
        });
        this.setFocusable(true);
        this.requestFocusInWindow();
        startSnake();
    }

    public void initStart() {
        layeredPaneGameStart = new JLayeredPane();
        layeredPaneGameStart.setOpaque(true);
        layeredPaneGameStart.setBounds(0, 0, this.getWidth(), this.getHeight());
        layeredPaneGameStart.setBackground(new Color(0, 0, 0));

        labelTitleStart = new JLabel("", new ImageIcon(PanelGame.class.getResource(sourceImage + "snake_title_100x100.png")), JLabel.CENTER);
        labelTitleStart.setBounds(getWidth() / 2 - 100 / 2, 150, 100, 100);
        layeredPaneGameStart.add(labelTitleStart, 0);

        buttonPlay = new JButton("NEW GAME");
        buttonPlay.setBounds(this.getWidth() / 2 - 200 / 2, 300, 200, 30);
        buttonPlay.setFont(new Font("JetBrains Mono", Font.BOLD, 17));
        buttonPlay.setForeground(new Color(255, 255, 255));
        buttonPlay.setBackground(new Color(255, 0, 0));
        buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPlay.setBorderPainted(false);
        buttonPlay.setFocusPainted(false);
        buttonPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeSnake();
                removeMoi();
                startSnake();
                layeredPaneGameStart.setVisible(false);
                layeredPaneGamePlay.setVisible(true);
                start = true;
            }
        });
        layeredPaneGameStart.add(buttonPlay, 0);

        buttonRemuse = new JButton("REMUSE");
        buttonRemuse.setBounds(this.getWidth() / 2 - 200 / 2, 300, 200, 30);
        buttonRemuse.setFont(new Font("JetBrains Mono", Font.BOLD, 17));
        buttonRemuse.setForeground(new Color(255, 255, 255));
        buttonRemuse.setBackground(new Color(255, 0, 0));
        buttonRemuse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonRemuse.setBorderPainted(false);
        buttonRemuse.setFocusPainted(false);
        buttonRemuse.setVisible(false);
        buttonRemuse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layeredPaneGameStart.setVisible(false);
                layeredPaneGamePlay.setVisible(true);
                if (phuongHuong.getLast() != 0) {
                    phuongHuong.addLast(0);
                }
                start = true;
            }
        });
        layeredPaneGameStart.add(buttonRemuse, 0);

        buttonQuit = new JButton("QUIT");
        buttonQuit.setBounds(this.getWidth() / 2 - 200 / 2, 350, 200, 30);
        buttonQuit.setFont(new Font("JetBrains Mono", Font.BOLD, 17));
        buttonQuit.setForeground(new Color(255, 255, 255));
        buttonQuit.setBackground(new Color(255, 0, 0));
        buttonQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonQuit.setBorderPainted(false);
        buttonQuit.setFocusPainted(false);
        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit = true;
            }
        });
        layeredPaneGameStart.add(buttonQuit, 0);

        labelCopyrightStart = new JLabel("Copyright of letienndat");
        labelCopyrightStart.setForeground(new Color(255, 255, 255));
        labelCopyrightStart.setFont(new Font("Inter", Font.PLAIN, 10));
        labelCopyrightStart.setBounds(0, layeredPaneGameStart.getHeight() - 90, layeredPaneGameStart.getWidth(), 13);
        labelCopyrightStart.setHorizontalAlignment(JLabel.CENTER);
        layeredPaneGameStart.add(labelCopyrightStart, 0);

        add(layeredPaneGameStart, 0);

        setVisible(true);
    }

    public void paintPointDisplay() {
        try {
            if (phuongHuong.getFirst() == 1) {
                linkedListToaDo.addFirst(new ToaDo(linkedListToaDo.get(0).getX() - 1, linkedListToaDo.get(0).getY()));
                panelPoint[linkedListToaDo.get(0).getX()][linkedListToaDo.get(0).getY()].setBackground(new Color(255, 255, 255));
                if (!snakeAnMoi()) {
                    panelPoint[linkedListToaDo.getLast().getX()][linkedListToaDo.getLast().getY()].setBackground(new Color(0, 0, 0));
                    linkedListToaDo.removeLast();
                } else {
                    taoMoi();
                }
                for (int i = 1; i < linkedListToaDo.size(); i++) {
                    panelPoint[linkedListToaDo.get(i).getX()][linkedListToaDo.get(i).getY()].setBackground(new Color(171, 211, 231));
                }
                if (phuongHuong.size() >= 2) {
                    phuongHuong.removeFirst();
                }
            } else if (phuongHuong.getFirst() == 2) {
                linkedListToaDo.addFirst(new ToaDo(linkedListToaDo.get(0).getX(), linkedListToaDo.get(0).getY() + 1));
                panelPoint[linkedListToaDo.get(0).getX()][linkedListToaDo.get(0).getY()].setBackground(new Color(255, 255, 255));
                if (!snakeAnMoi()) {
                    panelPoint[linkedListToaDo.getLast().getX()][linkedListToaDo.getLast().getY()].setBackground(new Color(0, 0, 0));
                    linkedListToaDo.removeLast();
                } else {
                    taoMoi();
                }
                for (int i = 1; i < linkedListToaDo.size(); i++) {
                    panelPoint[linkedListToaDo.get(i).getX()][linkedListToaDo.get(i).getY()].setBackground(new Color(171, 211, 231));
                }
                if (phuongHuong.size() >= 2) {
                    phuongHuong.removeFirst();
                }
            } else if (phuongHuong.getFirst() == 3) {
                linkedListToaDo.addFirst(new ToaDo(linkedListToaDo.get(0).getX() + 1, linkedListToaDo.get(0).getY()));
                panelPoint[linkedListToaDo.get(0).getX()][linkedListToaDo.get(0).getY()].setBackground(new Color(255, 255, 255));
                if (!snakeAnMoi()) {
                    panelPoint[linkedListToaDo.getLast().getX()][linkedListToaDo.getLast().getY()].setBackground(new Color(0, 0, 0));
                    linkedListToaDo.removeLast();
                } else {
                    taoMoi();
                }
                for (int i = 1; i < linkedListToaDo.size(); i++) {
                    panelPoint[linkedListToaDo.get(i).getX()][linkedListToaDo.get(i).getY()].setBackground(new Color(171, 211, 231));
                }
                if (phuongHuong.size() >= 2) {
                    phuongHuong.removeFirst();
                }
            } else if (phuongHuong.getFirst() == 4) {
                linkedListToaDo.addFirst(new ToaDo(linkedListToaDo.get(0).getX(), linkedListToaDo.get(0).getY() - 1));
                panelPoint[linkedListToaDo.get(0).getX()][linkedListToaDo.get(0).getY()].setBackground(new Color(255, 255, 255));
                if (!snakeAnMoi()) {
                    panelPoint[linkedListToaDo.getLast().getX()][linkedListToaDo.getLast().getY()].setBackground(new Color(0, 0, 0));
                    linkedListToaDo.removeLast();
                } else {
                    taoMoi();
                }
                for (int i = 1; i < linkedListToaDo.size(); i++) {
                    panelPoint[linkedListToaDo.get(i).getX()][linkedListToaDo.get(i).getY()].setBackground(new Color(171, 211, 231));
                }
                if (phuongHuong.size() >= 2) {
                    phuongHuong.removeFirst();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            lost = true;
            removeSnake();
            removeMoi();
        }
    }

    public boolean isLost() {
        return lost;
    }

    public void removeSnake() {
        temp = -1;
        phuongHuong.clear();
        phuongHuong.addLast(0);
        if (linkedListToaDo.getFirst().getX() < 0 || linkedListToaDo.getFirst().getX() >= panelPoint.length ||
                linkedListToaDo.getFirst().getY() < 0 || linkedListToaDo.getFirst().getY() >= panelPoint[0].length) {
            linkedListToaDo.removeFirst();
        }
        linkedListToaDo.forEach(i -> {
            panelPoint[i.getX()][i.getY()].setBackground(new Color(0, 0, 0));
        });
        linkedListToaDo.clear();
    }

    public void removeMoi() {
        panelPoint[toaDoMoi.getX()][toaDoMoi.getY()].setBackground(new Color(0, 0, 0));
    }

    public void startSnake() {
        lost = false;
        phuongHuong = new LinkedList<>();
        phuongHuong.addLast(0);
        linkedListToaDo = new LinkedList<>();
        linkedListToaDo.addFirst(new ToaDo());
        panelPoint[linkedListToaDo.get(0).getX()][linkedListToaDo.get(0).getY()].setBackground(new Color(255, 255, 255));
        labelOtherScore2.setText("0");
        labelOtherHighScore2.setText(readFile());

        taoMoi();
    }

    public void taoMoi() {
        while (true) {
            int x = (int) (Math.random() * (panelPoint.length) + 0);
            int y = (int) (Math.random() * (panelPoint[0].length) + 0);
            for (ToaDo toaDo : linkedListToaDo) {
                if (toaDo.getX() != x && toaDo.getY() != y) {
                    toaDoMoi = new ToaDo(x, y);
                    panelPoint[toaDoMoi.getX()][toaDoMoi.getY()].setBackground(new Color(255, 0, 0));
                    return;
                }
            }
        }
    }

    public boolean snakeAnMoi() {
        if (linkedListToaDo.getFirst().equals(toaDoMoi)) {
            labelOtherScore2.setText(String.valueOf(linkedListToaDo.size() - 1));
            if (highScore(Integer.parseInt(labelOtherScore2.getText()), Integer.parseInt(labelOtherHighScore2.getText()))) {
                labelOtherHighScore2.setText(labelOtherScore2.getText());
                writeFile(labelOtherHighScore2.getText());
            }
            return true;
        }
        return false;
    }

    public boolean snakeCanChinhMinh() {
        ToaDo a = linkedListToaDo.getFirst();
        for (int i = 3; i < linkedListToaDo.size(); i++) {
            if (a.equals(linkedListToaDo.get(i))) {
                removeSnake();
                removeMoi();
                return true;
            }
        }
        return false;
    }

    public boolean isWin() {
        return linkedListToaDo.size() == panelPoint.length * panelPoint[0].length;
    }

    public int timeSleep() {
        if (linkedListToaDo.size() <= 40) {
            return 150 - (linkedListToaDo.size() - 1);
        }
        return 40;
    }

    public boolean isQuit() {
        return quit;
    }

    public boolean isStart() {
        return start;
    }

    public String readFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(PanelGame.class.getResourceAsStream(sourceScore + "score.txt")));
            if (br.ready()) {
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public void writeFile(String s) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(PanelGame.class.getResource(sourceScore + "score.txt").toURI()))));
            bw.write(s);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public boolean highScore(int a, int b) {
        return a > b;
    }
}
