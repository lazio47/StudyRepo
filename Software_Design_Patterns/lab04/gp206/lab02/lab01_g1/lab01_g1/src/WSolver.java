import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WSolver {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java WSolver <input_file>");
            System.exit(1);
        }
        String input = args[0];
        File file = new File(input);
        Scanner scanner = new Scanner(file);
        ArrayList<String> puzzle = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                System.out.println("Empty line in input file");
                System.exit(1);
            }
            if (line.contains(";") || line.contains(",") || line.contains(" ")) {
                String[] wordList = line.split("[,; ]");
                for (String word : wordList) {
                    words.add(word);
                }
            } else {
                
                puzzle.add(line);
            }
        }

        scanner.close();

        // o puzzle é um quadrado NxN, no maximo com 40x40, senao nao é valido
        int N = puzzle.size();
        if (N < 1 || N > 40) {
            System.out.println("Invalid puzzle size");
            System.exit(1);
        }   
        for (String row : puzzle) {
            if (row.length() != N) {
                System.out.println("Puzzle is not a square");
                System.exit(1);
            }
        }

        // puzzle esta em maiusculas
        for (String row : puzzle) {
            for (char c : row.toCharArray()) {
                if (Character.isLowerCase(c)){
                    System.out.println("Puzzle is not uppercase");
                    System.exit(1);
                }
            }
        }

        // as palavras estao em minusculas e sao compostas por caracteres alfabeticos
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    System.out.println("Word is not lowercase");
                    System.exit(1);
                }
                if (!Character.isAlphabetic(c)) {
                    System.out.println("Word is not alphabetic");
                    System.exit(1);
                }
            }
        }        

        // as palavras podem condem conter palavras iguais (por exemplo, pode conter BAG e RUTABAGA) , nestes casos a palavra maior é a que conta
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).contains(words.get(j))) {
                    words.remove(j);
                    j--;
                }
                if (words.get(j).contains(words.get(i))) {
                    words.remove(i);
                    i--;
                    break;
                }
            }
        }


        WordSearch ws = new WordSearch(puzzle, words);
        ws.solve();

    }
}


