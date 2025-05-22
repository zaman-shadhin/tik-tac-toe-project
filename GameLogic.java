package project;

import javax.swing.*;
import java.awt.*;

public class GameLogic {
    GameBoard board;

    public GameLogic(GameBoard board) {
        this.board = board;
    }

    public void checkWinner() {
        String[] b = new String[9];
        for (int i = 0; i < 9; i++) {
            b[i] = board.buttons[i].getText();
        }

        int[][] winCombos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // cols
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] combo : winCombos) {
            if (b[combo[0]].equals("X") && b[combo[1]].equals("X") && b[combo[2]].equals("X")) {
                xWins(combo[0], combo[1], combo[2]);
                return;
            }
            if (b[combo[0]].equals("O") && b[combo[1]].equals("O") && b[combo[2]].equals("O")) {
                oWins(combo[0], combo[1], combo[2]);
                return;
            }
        }

        boolean draw = true;
        for (JButton button : board.buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            draw();
        }
    }

    public void xWins(int a, int b, int c) {
        winDisplay(a, b, c, "X wins");
    }

    public void oWins(int a, int b, int c) {
        winDisplay(a, b, c, "O wins");
    }

    public void draw() {
        for (JButton button : board.buttons) {
            button.setEnabled(false);
        }
        board.textfield.setText("DRAW");
    }

    public void winDisplay(int a, int b, int c, String message) {
        board.buttons[a].setBackground(Color.GREEN);
        board.buttons[b].setBackground(Color.GREEN);
        board.buttons[c].setBackground(Color.GREEN);

        for (JButton button : board.buttons) {
            button.setEnabled(false);
        }
        board.textfield.setText(message);
    }
}
