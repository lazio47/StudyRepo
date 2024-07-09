
import java.util.Scanner;

public class TermFilter extends TextReaderDecorator {

    private Scanner scanner;

    public TermFilter(TextReaderInterface wrappee) {
        super(wrappee);
    }

    public boolean hasNext() {
        if (scanner == null || !scanner.hasNext()) {
            if (!super.hasNext())
                return false;
            scanner = new Scanner(super.next());

        }

        return scanner.hasNext();

    }

    public String next() {
        return scanner.next();
    }
}
