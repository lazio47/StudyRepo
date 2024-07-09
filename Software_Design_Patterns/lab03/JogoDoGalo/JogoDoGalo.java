
public class JogoDoGalo implements JGaloInterface {

    public char actualPlayer = 'X';
    public int numJogada = 0;
    public final int MAXJOGADAS = 9;
    public char[][] jogo = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    @Override
    public char checkResult() {
        // TODO Auto-generated method stub
        if(checkTable()){
            return actualPlayer == 'X' ? 'O' : 'X';
        }
        return ' ';
    }
    
    public boolean checkTable(){
        for (int i = 0; i < 3; i++) {
            if (isColumnDone(i) || isRowDone(i)) {
                return true;
            }
        }
        if(areDiagonalsDone()){
            return true;
        }
        return false;
    }

    public boolean isRowDone(int col){

        return !isEmpty(0, col) && jogo[0][col] == jogo[1][col] && jogo[1][col] == jogo[2][col];
    }

    public boolean isColumnDone(int lin){
        return !isEmpty(lin, 0) && jogo[lin][0] == jogo[lin][1] && jogo[lin][1] == jogo[lin][2];
    }

    public boolean areDiagonalsDone(){
        return isMainDiagonalDone() || isSecondaryDiagonalDone();
    }

    public boolean isMainDiagonalDone(){
        return !isEmpty(1, 1) && jogo[0][0] == jogo[1][1] && jogo[0][0] == jogo[2][2];
    }

    public boolean isSecondaryDiagonalDone(){
        return !isEmpty(1, 1) && jogo[0][2] == jogo[1][1] && jogo[0][2] == jogo[2][0];
    }

    public boolean isEmpty(int lin, int col){
        return jogo[lin][col] == ' ' ? true : false;
    }

    @Override
    public char getActualPlayer() {
        // TODO Auto-generated method stub
        return actualPlayer;
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        if (numJogada == MAXJOGADAS || checkResult() != ' '){
            return true;
        }
        return false;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        // TODO Auto-generated method stub
        if(jogo[lin-1][col-1] == ' '){
            jogo[lin-1][col-1] = getActualPlayer();
            numJogada++;
            actualPlayer = actualPlayer == 'X' ? 'O' : 'X';
            return true;
        }
        return false;
    }
    
}
