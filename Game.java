import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class Game {
    Playground pg = new Playground();
    Blocks blocks = new Blocks();
    JFrame game;
    JPanel gameZone;
    JLabel[][] label;
    JButton back, swap, randomBlocks ,clear;
    int[][] pointLabel;
    int[][] drop;

    Game() {
        game = new JFrame("Terttist");
        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setBackground(Color.WHITE);
        detailComponent();
        game.setLayout(null);
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    void detailComponent() {
        gameZone = new JPanel();
        gameZone.setBackground(Color.BLACK);
        gameZone.setBounds(460, 0, 1000, 1080);

        back = new JButton("Back");
        back.setBounds(0, 0, 100, 50);
        back.setFont(new Font(null, Font.PLAIN, 20));
        back.setFocusable(false);
        back.setBackground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(new LineBorder(Color.BLACK));

        swap = new JButton("Swap Blocks");
        swap.setBounds(0, 50, 200, 50);
        swap.setFont(new Font(null, Font.PLAIN, 20));
        swap.setFocusable(false);
        swap.setBackground(Color.WHITE);
        swap.setFocusPainted(false);
        swap.setBorder(new LineBorder(Color.BLACK));

        randomBlocks = new JButton("Random Blocks");
        randomBlocks.setBounds(0, 100, 200, 50);
        randomBlocks.setFont(new Font(null, Font.PLAIN, 20));
        randomBlocks.setFocusable(false);
        randomBlocks.setBackground(Color.WHITE);
        randomBlocks.setFocusPainted(false);
        randomBlocks.setBorder(new LineBorder(Color.BLACK));

        clear = new JButton("Clear Blocks");
        clear.setBounds(0, 150, 200, 50);
        clear.setFont(new Font(null, Font.PLAIN, 20));
        clear.setFocusable(false);
        clear.setBackground(Color.WHITE);
        clear.setFocusPainted(false);
        clear.setBorder(new LineBorder(Color.BLACK));

        playGround();
        buttonAction();

        game.add(back);
        game.add(swap);
        game.add(randomBlocks);
        game.add(clear);
        game.add(gameZone);
    }

    void playGround() {
        label = new JLabel[pg.getJLabel().length][];
        int idx = 0;
        for(JLabel[] p : pg.getJLabel()) {
            label[idx] = p;
            idx++;
        }
        for(int i = 0; i < label.length; i++) {
            for(int j = 0; j < label[0].length; j++) {
                game.add(label[i][j]);
            }
        }
        pointLabel = new int[label.length][label[0].length];
    }

    void mixWithPlayground(int[][] arr, int x, int y) {
        int a = 0;
        int b = 0;
        for(int i = x; i < arr.length + x; i++) {
            for(int j = y; j < arr[0].length + y; j++) {
                if(arr[a][b] == 1) {
                    pointLabel[i][j] = 1;
                }
                b++;
            }
            a++;
            b = 0;
        }
    }

    void clearPlayground(int[][] arr, int x, int y) {
        int a = 0;
        int b = 0;
        for(int i = x; i < arr.length + x; i++) {
            for(int j = y; j < arr[0].length + y; j++) {
                if(arr[a][b] == 1) {
                    pointLabel[i][j] = 0;
                    pg.setDefaultColorLabel(x, y);
                }
                b++;
            }
            a++;
            b = 0;
        }
    }

    int[][] swapShape(int[][] arr) {
        int[][] newDrop = new int[arr[0].length][arr.length];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                newDrop[j][arr.length - i - 1] = arr[i][j];
            }
        }
        return newDrop;
    }

    void checkColor() {
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                if(pointLabel[i][j] == 1) {
                    pg.setColorLabel(i, j);
                }
            }
        }
    }

    void buttonAction() {
        MyActionListener listener = new MyActionListener();
        back.addActionListener(listener);
        swap.addActionListener(listener);
        randomBlocks.addActionListener(listener);
        clear.addActionListener(listener);
    }
    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            if(source == back) {
                Main main = new Main();
                game.dispose();
            }
            else if(source == swap) {
                clearPlayground(drop, 0, 0);
                drop = swapShape(drop);
                for(int[] e1 : drop) {
                    System.out.println(Arrays.toString(e1));
                }
                mixWithPlayground(drop, 0, 0);
                checkColor();
            }
            else if(source == randomBlocks) {
                if(drop != null) {
                    clearPlayground(drop, 0, 0);
                }
                Random rand = new Random();
                int n = rand.nextInt(7) + 1;
                if(n == 1) {
                    drop = blocks.getO();
                }
                else if(n == 2) {
                    drop = blocks.getI();
                }
                else if(n == 3) {
                    drop = blocks.getS();
                }
                else if(n == 4) {
                    drop = blocks.getZ();
                }
                else if(n == 5) {
                    drop = blocks.getL();
                }
                else if(n == 6) {
                    drop = blocks.getJ();
                }
                else if(n == 7) {
                    drop = blocks.getT();
                }
                System.out.println("Num = " + n);
                mixWithPlayground(drop, 0, 0);
                checkColor();
            }
            else if(source == clear) {
                    clearPlayground(drop, 0, 0);
            }
        }
    }
}
