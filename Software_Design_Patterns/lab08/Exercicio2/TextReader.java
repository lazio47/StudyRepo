import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class TextReader implements TextReaderInterface {

    private Scanner scanner;

    public TextReader(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public String next() {
        return scanner.nextLine();
    }
}
