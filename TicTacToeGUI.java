package Tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
    
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel = new JLabel("Player X's turn");
    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if (!btnClicked.getText().equals("")) return;

        btnClicked.setText(String.valueOf(currentPlayer));
        if (checkWin()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("Game is a draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (match(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (match(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return match(buttons[0][0], buttons[1][1], buttons[2][2]) ||
               match(buttons[0][2], buttons[1][1], buttons[2][0]);
    }
    private boolean match(JButton b1, JButton b2, JButton b3) {
        String t1 = b1.getText(), t2 = b2.getText(), t3 = b3.getText();
        return !t1.equals("") && t1.equals(t2) && t2.equals(t3);
    }
    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals("")) return false;
        return true;
    }
    private void disableButtons() {
        for (JButton[] row : buttons)
            for (JButton button : row)
                button.setEnabled(false);
    }

    
}