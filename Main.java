import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    JFrame main;
    JPanel panel;
    JButton start;
    JLabel name;

    Main() {
        main = new JFrame("Terttist");
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        detailSW();
        main.setLayout(null);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void detailSW() {
        panel = new JPanel();
        panel.setBackground(null);
        panel.setBounds(0, 0, 1920, 1080);
        panel.setBackground(Color.WHITE);

        name = new JLabel("Tertist Game");
        name.setBounds(660, 100, 800, 100);
        name.setForeground(Color.BLACK);
        name.setFont(new Font(null, Font.PLAIN, 100));
        name.setHorizontalTextPosition(JLabel.CENTER);

        start = new JButton("Start");
        start.setBounds(860, 500, 200, 100);
        start.setFont(new Font(null, Font.PLAIN, 40));
        start.setFocusable(false);
        start.setBackground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(new LineBorder(Color.BLACK));

        
        buttonAction();

        main.add(start);  main.add(name);  main.add(panel);    
    }

    void buttonAction() {
        MyActionListener listener = new MyActionListener();
        start.addActionListener(listener);
    }
    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            if(source == start) {
                Game game = new Game();
                main.dispose();
            }
        }
    }
    public static void main(String[] args) {
        Main start = new Main();
    }
}