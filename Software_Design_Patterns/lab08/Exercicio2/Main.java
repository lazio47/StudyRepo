import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        TextReaderInterface reader = new VowelFilter(new TermFilter(new TextReader("exemplo2.txt")));

        while (reader.hasNext()) {
            System.out.println(reader.next());            
        }

    }

}
