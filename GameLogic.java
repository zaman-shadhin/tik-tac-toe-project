package Tictactoe;  
public class GameLogic {
    private GameBoard board;
    HumanPlayer humanPlayer1;
    HumanPlayer humanPlayer2;
    

    public GameLogic() {
        board = new GameBoard();
        humanPlayer1 = new HumanPlayer('X');
        humanPlayer2 = new HumanPlayer('O');
     
    }

    public void playGame() {
        HumanPlayer currentPlayer = humanPlayer1;
        while (true) {
            board.printBoard();
            currentPlayer.makeMove(board);
            if (board.checkWin(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                break;
            }
            if (board.isFull()) {
                board.printBoard();
                System.out.println("The game is a draw!");
                break;
            }
            currentPlayer = (currentPlayer == humanPlayer1) ? humanPlayer2 : humanPlayer1;
        }
    }
}