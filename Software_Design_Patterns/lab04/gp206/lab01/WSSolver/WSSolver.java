package lab01.WSSolver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import lab01.WordDetails;

import java.util.ArrayList;

public class WSSolver {
    static final int MAX_SIZE = 40;
    static String [] [] puzzle;
    static String [] [] solution;
    static List<String> wordsList = new ArrayList<String>();
    static List <WordDetails> wordsFound = new ArrayList<>();
    static List <WordDetails> wordsRepeated = new ArrayList<>();


    static Scanner readfile (String filename) {
        try {
            return new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File does not exist.");
            System.exit(1);
            return null;
        }
    }

    static boolean isSquare(Scanner file) {

        String firstLine = file.nextLine();

        if (firstLine.isEmpty()) {
            System.err.println("ERROR: The file does not contain a valid puzzle.");
            System.exit(1);
        }

        if (firstLine.length() > MAX_SIZE) {
            System.err.println("ERROR: Puzzle is too long. A " + MAX_SIZE + "x" + MAX_SIZE + " grid is the maximum.");
            System.exit(1);
        }

        puzzle = new String[firstLine.length()][firstLine.length()];

        puzzle[0] = firstLine.split("");

        String line = null;

        for (int i = 1; i < puzzle[0].length; i++) {
            line = file.nextLine();
            if (line.isEmpty()) {
                System.err.println("ERROR: The file does not contain a valid puzzle.");
                System.exit(1);
            }
            if (line.length() != firstLine.length()) {
                System.err.println("ERROR: Puzzle is not a square.");
                System.exit(1);
            }
            for (int j = 0; j < line.length(); j++) {
                if (!Character.isUpperCase(line.charAt(j))) {
                    System.err.println("ERROR: The puzzle must contain only uppercase letters.");
                    System.exit(1);
                }
            }
            puzzle[i] = line.split("");
        }
        return true;
    }

    static void readWords(Scanner file) {
        
        while (file.hasNextLine()) {
            String extraline = file.nextLine();
            if (extraline.isEmpty()|| extraline.matches("[A-Z, ;]+")) {
                System.err.println("ERROR: The file does not contain a valid puzzle.");
                System.exit(1);
            }
            String [] words = extraline.split("[,; ]");
            for (String word : words) {
                if (wordsList.contains(word)) {
                    System.err.println("ERROR: The file contains repeated words.");
                    System.exit(1);
                }
                if (word.length() > puzzle.length) {
                    System.err.println("ERROR: The word " + word + " is too long for the puzzle.");
                    System.exit(1);
                }
                if (!word.matches("[a-zA-Z]+")) {
                    System.err.println("ERROR: The word " + word + " contains invalid characters.");
                    System.exit(1);
                }
                wordsList.add(word);
            }
        }
    }

    static boolean rightSearch (String word, int row, int col) {
        if (col + word.length() <= puzzle.length) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row][col + i].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean leftSearch (String word, int row, int col) {
        if (col - word.length() >= -1) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row][col - i].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean upSearch (String word, int row, int col) {
        if (row - word.length() >= -1) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row - i][col].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean downSearch (String word, int row, int col) {
        if (row + word.length() <= puzzle.length) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row + i][col].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean upRightSearch (String word, int row, int col) {
        if (row - word.length() >= -1 && col + word.length() <= puzzle.length) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row - i][col + i].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean upLeftSearch (String word, int row, int col) {
        if (row - word.length() >= -1 && col - word.length() >= -1) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row - i][col - i].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean downRightSearch (String word, int row, int col) {
        if (row + word.length() <= puzzle.length && col + word.length() <= puzzle.length) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row + i][col + i].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static boolean downLeftSearch (String word, int row, int col) {
        if (row + word.length() <= puzzle.length && col - word.length() >= -1) {
            for (int i = 0; i < word.length(); i++) {
                if (!puzzle[row + i][col - i].equals(word.substring(i, i + 1))) {
                    return false;
                } 
            }
                return true;
            }
            return false;
        }

    static void findWord (String word) {
        String UpperWord = word.toUpperCase();
        WordDetails x;

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j].equals(UpperWord.substring(0, 1))) {
                    if (rightSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "Right");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (leftSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "Left");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (upSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "Up");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (downSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "Down");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (upRightSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "UpRight");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (upLeftSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "UpLeft");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (downRightSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "DownRight");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                    if (downLeftSearch(UpperWord, i, j)) {
                        x = new WordDetails(word, word.length(), i+1, j+1, "DownLeft");
                        if (!wordsFound.contains(x)) {
                            wordsFound.add(x);
                        } else {
                            wordsRepeated.add(x);
                        }
                    }
                }
            }
        }
        
    }

    static void createSolution( String [] [] solution, List <WordDetails> wordsFound) {
      for (int i = 0; i < solution.length; i++) {
          for (int j = 0; j < solution[i].length; j++) {
              solution[i][j] = ".";
          }
      }
        for (WordDetails word : wordsFound) {
            int row = word.getRow() - 1;
            int col = word.getCol() - 1;
            String direction = word.getDirection();
            for (int i = 0; i < word.getSize(); i++) {
                if (direction.equals("Right")) {
                    solution[row][col + i] = puzzle[row][col + i];
                }
                if (direction.equals("Left")) {
                    solution[row][col - i] = puzzle[row][col - i];
                }
                if (direction.equals("Up")) {
                    solution[row - i][col] = puzzle[row - i][col];
                }
                if (direction.equals("Down")) {
                    solution[row + i][col] = puzzle[row + i][col];
                }
                if (direction.equals("UpRight")) {
                    solution[row - i][col + i] = puzzle[row - i][col + i];
                }
                if (direction.equals("UpLeft")) {
                    solution[row - i][col - i] = puzzle[row - i][col - i];
                }
                if (direction.equals("DownRight")) {
                    solution[row + i][col + i] = puzzle[row + i][col + i];
                }
                if (direction.equals("DownLeft")) {
                    solution[row + i][col - i] = puzzle[row + i][col - i];
                }
            }
        }
    }


    public static void main(String[] args) {

        Scanner file = readfile(args[0]);

        isSquare(file);
        readWords(file);
        

        for (String word : wordsList) {
            findWord(word);
        }
        
        for (WordDetails wordDetails : wordsFound) {
            System.out.println(wordDetails);
        }

        solution = new String [puzzle.length][puzzle.length];

        createSolution(solution, wordsFound);
        System.out.println("");
        
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                System.out.print(solution[i][j]);
            }
            System.out.println();
        }
        
        file.close();
    } 


}