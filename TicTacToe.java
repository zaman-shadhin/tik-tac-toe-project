import java.util.Scanner;

public class TicTacToe {
    static char[][] board = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
    static char human = 'X', ai = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameOn = true;

        System.out.print("Do you want to play first? (yes/no): ");
        String choice = scanner.next();
        boolean humanTurn = choice.equalsIgnoreCase("yes");

        while (gameOn) {
            printBoard();
            if (humanTurn) {
                humanMove(scanner);
            } else {
                System.out.println("AI is making a move...");
                aiMove();
            }

            if (checkWin(human)) {
                printBoard();
                System.out.println("You win!");
                break;
            } else if (checkWin(ai)) {
                printBoard();
                System.out.println("AI wins!");
                break;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            humanTurn = !humanTurn;
        }

        scanner.close();
    }

    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void humanMove(Scanner scanner) {
        int row, col;
        while (true) {
            System.out.print("Enter row and column (0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = human;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public static void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1, bestCol = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = ai;
                    int score = minimax(false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        board[bestRow][bestCol] = ai;
    }

    public static int minimax(boolean isMaximizing) {
        if (checkWin(ai)) return 1;
        if (checkWin(human)) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? ai : human;
                    int score = minimax(!isMaximizing);
                    board[i][j] = ' ';
                    bestScore = isMaximizing ?
                        Math.max(score, bestScore) :
                        Math.min(score, bestScore);
                }
            }
        }

        return bestScore;
    }

    public static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player &&
                 board[i][1] == player &&
                 board[i][2] == player) ||
                (board[0][i] == player &&
                 board[1][i] == player &&
                 board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) ||
               (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player);
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}

