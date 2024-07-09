import java.io.FileNotFoundException;

public class WSSolver {
    public static void main(String[] args) throws FileNotFoundException {
        WordSearchSolver wss = new WordSearchSolver(args[0]);
        if(WordSearchSolver.validadePuzzle){
            wss.printOutput();
            wss.printMatriz();
        }else {
            System.err.println("Puzzle Inválido!");
        }
    }
}
