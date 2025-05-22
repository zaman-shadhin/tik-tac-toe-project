package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameBoard implements ActionListener {

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    Random random = new Random();
    GameLogic logic;

    public GameBoard() {
        logic = new GameLogic(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(40, 80, 237));
        textfield.setFont(new Font("Arial", Font.BOLD, 30));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setOpaque(true);
        textfield.setText("Tic-Tac-Toe");

        title_panel.setLayout(new BorderLayout());
        title_panel.add(textfield, BorderLayout.CENTER);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(8, 28, 74));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.setVisible(true);

        firstTurn();
    }

    public void firstTurn() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    if (player1_turn) {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("X");
                        textfield.setText("O turn");
                    } else {
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("O");
                        textfield.setText("X turn");
                    }
                    player1_turn = !player1_turn;
                    logic.checkWinner();
                }
            }
        }
    }
}
