import java.io.*;
import java.util.*;

public class WSGenerator {
    public static void main(String[] args) {
        if (args.length != 6 || !args[0].equals("-i") || !args[2].equals("-s") || !args[4].equals("-o")) {
            System.out.println("Usage: java WSGenerator -i <input_file> -s <dimension> -o <output_file>");
            return;
        }

        String inputFile = args[1];
        int dimension = Integer.parseInt(args[3]);
        String outputFile = args[5];

        try {
            List<String> words = readWords(inputFile);
            char[][] puzzle = generatePuzzle(words, dimension);
            savePuzzle(puzzle, outputFile);
            System.out.println("Puzzle generated successfully and saved to " + outputFile);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static List<String> readWords(String inputFile) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitWords = line.split("[,;\\s]+");
                for (String word : splitWords) {
                    if (!word.isEmpty()) {
                        words.add(word.toUpperCase());
                    }
                }
            }
        }
        return words;
    }

    private static char[][] generatePuzzle(List<String> words, int dimension) {
        char[][] puzzle = new char[dimension][dimension];
        Random random = new Random();
        Set<String> usedWords = new HashSet<>();

        for (String word : words) {
            // Check if the word has already been used
            if (usedWords.contains(word)) continue;

            boolean wordPlaced = false;
            while (!wordPlaced) {
                int startX = random.nextInt(dimension);
                int startY = random.nextInt(dimension);
                int directionX = random.nextInt(3) - 1;
                int directionY = random.nextInt(3) - 1;

                if (directionX == 0 && directionY == 0) continue;

                boolean fitsHorizontally = directionX != 0 && dimension - startX >= word.length();
                boolean fitsVertically = directionY != 0 && dimension - startY >= word.length();

                if (fitsHorizontally || fitsVertically) {
                    boolean fits = true;
                    for (int i = 0; i < word.length(); i++) {
                        int x = startX + i * directionX;
                        int y = startY + i * directionY;
                        if (x < 0 || x >= dimension || y < 0 || y >= dimension || (puzzle[x][y] != '\0' && puzzle[x][y] != word.charAt(i))) {
                            fits = false;
                            break;
                        }
                    }

                    if (fits) {
                        for (int i = 0; i < word.length(); i++) {
                            int x = startX + i * directionX;
                            int y = startY + i * directionY;
                            puzzle[x][y] = word.charAt(i);
                        }
                        wordPlaced = true;
                        usedWords.add(word);
                    }
                }
            }
        }

        // Fill remaining empty spaces with random letters
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (puzzle[i][j] == '\0') {
                    puzzle[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }

        return puzzle;
    }

    private static void savePuzzle(char[][] puzzle, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < puzzle.length; i++) {
                for (int j = 0; j < puzzle[i].length; j++) {
                    writer.write(puzzle[i][j]);
                }
                writer.newLine();
            }
        }
    }
}
