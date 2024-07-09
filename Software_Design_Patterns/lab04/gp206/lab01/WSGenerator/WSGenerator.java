package lab01.WSGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import lab01.WSGenerator.Direction;
import lab01.WordDetails;

public class WSGenerator {
    static final int MAX_SIZE = 40;
    static List<String> temp = new ArrayList<>();
    static List<WordDetails> wordsList = new ArrayList<>();
    static String[][] puzzle;
    static Random random = new Random();

    static Scanner readfile(String filename) {
        try {
            return new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File does not exist.");
            System.exit(1);
            return null;
        }
    }

    static void readWords(Scanner file, int size) {
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (line.isEmpty() || line.matches("[A-Z, ;]+")) {
                System.err.println("ERROR: The file does not contain a valid word list.");
                System.exit(1);
            }
            String[] words = line.split("[,; ]");
            for (String word : words) {
                if (word.length() > size) {
                    System.err.println("ERROR: The word " + word + " is too long.");
                    System.exit(1);
                }
                if (word.length() < 1) {
                    System.err.println("ERROR: The word " + word + " is too short.");
                    System.exit(1);
                }
                if (temp.contains(word)) {
                    System.err.println("ERROR: The word " + word + " is repeated.");
                    System.exit(1);
                }
                if (!word.matches("[a-zA-Z]+")) {
                    System.err.println("ERROR: The word " + word + " is not valid.");
                    System.exit(1);
                }
                temp.add(word.toUpperCase()); 
                wordsList.add(new WordDetails(word));
            }
        }
    }

    static void generatePuzzle(int size) {
        if (size > MAX_SIZE) {
            System.err.println("ERROR: The size of the puzzle is too big.");
            System.exit(1);
        }
        puzzle = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[i][j] = " "; // Initialize with spaces.
            }
        }
        for (String word : temp) {
            if (!tryPlaceWord(word)) {
                System.err.println("ERROR: Could not place all words.");
                System.exit(1);
            }
        }
    }

    static boolean tryPlaceWord(String word) {
        int maxAttempts = 100; // Limit for placement attempts.
        Direction[] directions = Direction.values();
    
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int row = random.nextInt(puzzle.length);
            int col = random.nextInt(puzzle[0].length);
            Direction direction = directions[random.nextInt(directions.length)];
    
            if (canPlaceWordAt(word, row, col, direction)) {
                placeWordAt(word, row, col, direction);
                updateWordDetails(word, row, col, direction); 
                return true;
            }
        }
        return false;
    }
    
    static void updateWordDetails(String word, int row, int col, Direction direction) {
        for (WordDetails wordDetails : wordsList) {
            if (wordDetails.getWord().equals(word)) {
                wordDetails.setRow(row + 1);
                wordDetails.setCol(col + 1);
                wordDetails.setDirection(direction.toString());
                return; // No need to keep searching

            }
        }
    }
    

    private static void placeWordAt(String word, int row, int col, Direction direction) {
        switch (direction) {
            case HORIZONTAL:
                for (int i = 0; i < word.length(); i++) {
                    puzzle[row][col + i] = String.valueOf(word.charAt(i));
                }
                break;
            case VERTICAL:
                for (int i = 0; i < word.length(); i++) {
                    puzzle[row + i][col] = String.valueOf(word.charAt(i));
                }
                break;
            case DIAGONAL_RIGHT:
                for (int i = 0; i < word.length(); i++) {
                    puzzle[row + i][col + i] = String.valueOf(word.charAt(i));
                }
                break;
            case DIAGONAL_LEFT:
                for (int i = 0; i < word.length(); i++) {
                    puzzle[row + i][col - i] = String.valueOf(word.charAt(i));
                }
                break;
            default:
                throw new UnsupportedOperationException("Unimplemented direction: " + direction);
        }
    }

    private static boolean canPlaceWordAt(String word, int row, int col, Direction direction) {
        switch (direction) {
            case HORIZONTAL:
                if (col + word.length() > puzzle[0].length) {
                    return false;
                }
                for (int i = 0; i < word.length(); i++) {
                    if (!puzzle[row][col + i].equals(" ") && !puzzle[row][col + i].equals(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
                return true;
            case VERTICAL:
                if (row + word.length() > puzzle.length) {
                    return false;
                }
                for (int i = 0; i < word.length(); i++) {
                    if (!puzzle[row + i][col].equals(" ") && !puzzle[row + i][col].equals(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
                return true;
            case DIAGONAL_RIGHT:
                if (row + word.length() > puzzle.length || col + word.length() > puzzle[0].length) {
                    return false;
                }
                for (int i = 0; i < word.length(); i++) {
                    if (!puzzle[row + i][col + i].equals(" ") && !puzzle[row + i][col + i].equals(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
                return true;
            case DIAGONAL_LEFT:
                if (row + word.length() > puzzle.length || col - word.length() + 1 < 0) {
                    return false;
                }
                for (int i = 0; i < word.length(); i++) {
                    if (!puzzle[row + i][col - i].equals(" ") && !puzzle[row + i][col - i].equals(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
                return true;
            default:
                throw new UnsupportedOperationException("Unimplemented direction: " + direction);
        }
    }

    static boolean canPlaceWordAt(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            if (!puzzle[row][col + i].equals(" ") && !puzzle[row][col + i].equals(String.valueOf(word.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    static void placeWordAt(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            puzzle[row][col + i] = String.valueOf(word.charAt(i));
        }
    }

    static void printPuzzle(String output) {
        try (PrintWriter writer = new PrintWriter(output)) {
            // Print the puzzle grid
            for (String[] row : puzzle) {
                for (String cell : row) {
                    writer.print(cell.equals(" ") ? (char) ('A' + random.nextInt(26)) : cell); 
                }
                writer.println();
            }

            // Imprime as palavras no fundo, separadas por vírgula
            for (int i = 0; i < temp.size(); i++) {
                writer.print(temp.get(i).toLowerCase());
                if (i < temp.size() - 1) {
                    writer.print("; "); 
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File does not exist.");
            System.exit(1);
        }
    }
    
    

    public static void main(String[] args) {
        String filename = null;
        String output = null;
        int size = 0;

        if (args.length != 6) {
            System.err.println("Usage: WSGeneratora -i <inputfile> -o <outputfile> -s <size>");
            System.exit(1);
        }

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-i":
                    filename = args[i + 1];
                    break;
                case "-o":
                    output = args[i + 1];
                    break;
                case "-s":
                    size = Integer.parseInt(args[i + 1]);
                    break;
                default:
                    System.err.println("Unknown argument: " + args[i]);
                    System.exit(1);
            }
        }

        Scanner file = readfile(filename);
        readWords(file, size);
        generatePuzzle(size);
        printPuzzle(output);
    }

}