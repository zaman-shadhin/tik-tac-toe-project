package Tictactoe;

public class HumanPlayer {
    private char symbol;

    public HumanPlayer(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void makeMove(GameBoard board) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int row, col;
        while (true) {
            System.out.print("Player " + symbol + ", enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (board.setMove(row, col, symbol)) {
                break;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }
}
