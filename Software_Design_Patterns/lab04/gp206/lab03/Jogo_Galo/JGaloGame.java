public class JGaloGame implements JGaloInterface {
    private char[][] board;
    private int NJogadas = 0;
    private char current_player;
    
    public JGaloGame() {
        board = new char[3][3];
        startBoard();
        current_player = 'X';
    }

    public JGaloGame(char player) {
        board = new char[3][3];
        startBoard();
        current_player = player;
    }

    private void startBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void switchplayer() {
        if (current_player == 'X') {
            current_player = 'O';
        }else{
            current_player = 'X';
        }
    }

    private boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    @Override
    public char getActualPlayer() {
        return current_player;
    }

    @Override
    public boolean setJogada(int row, int col) {
        if (board[row-1][col-1] == ' ') {
            board[row-1][col-1] = getActualPlayer();
            switchplayer();
            NJogadas++;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinished() {
        if (NJogadas == 9) {
            return true;
        }
        if (hasWon('X') || hasWon('O')) {
            return true;
        }
        return false;
    }

    @Override
    public char checkResult() {
        if (hasWon('X')) {
            return 'X';
        }
        if (hasWon('O')) {
            return 'O';
        }
        return ' ';
    }
}
