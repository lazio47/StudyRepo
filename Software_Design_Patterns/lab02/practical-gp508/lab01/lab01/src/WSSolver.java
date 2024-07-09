import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files


public class WSSolver {
    private static List<Result> results = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java Program <filename> <outputfile> ");
            System.exit(1); // Exit with an error code
        }
        List <String> words = new ArrayList<>();
        // String [][] lettersoup = new String [40][40];
        List<Character []> letterSoup = new ArrayList<>();
        String outputPath = args[1];
        try {
            File sopa_de_letras = new File(args[0]);
            Scanner input = new Scanner(sopa_de_letras);
            int nlines = 0;
            int size_of_line = 0;
            while (input.hasNextLine()) {
                String data = input.nextLine();
                if (nlines == 0) {
                    size_of_line = data.length();
                    if (size_of_line > 40) {
                        System.err.println("Size of soup over the limit");
                        System.exit(1);
                    }
                }
                if (nlines >= size_of_line) {
                    String[] keywords =  data.split("\\W");
                    for (int i=0 ;i<keywords.length; i++) {
                        words.add(keywords[i]);
                    }
                    // continue;
                } else {
                    Character [] line = new Character[size_of_line];
                    for (int i=0; i<size_of_line; i++) {
                        if (!Character.isAlphabetic(data.charAt(i))) {
                            System.err.println("Character  " + data.charAt(i) + " is not alphabetic.");
                            System.exit(1);
                        }
                        if (!Character.isUpperCase(data.charAt(i))) {
                            System.err.println("Character  " + data.charAt(i) + " is not upper case.");
                            System.exit(1);
                        }
                        line[i] = data.charAt(i);
                    }
                    letterSoup.add(line);
                    nlines++;
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.print("An error occurred. Check files names!");
        }
        
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath))) {
            // Redirect System.out to the file
            System.setOut(new PrintStream(new FileOutputStream(outputPath)));
            System.out.println("Word Scramble\n");
            for (int i = 0; i < letterSoup.size(); i++) {
                Character [] line_soup = letterSoup.get(i);
                for (int j = 0; j < line_soup.length; j++) {
                    System.out.printf("%s ",line_soup[j]);
                }                
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < words.size(); i++) {
                findWords(letterSoup, words.get(i));
            }
            for (int j = 0; j < results.size(); j++) {
                System.out.print(results.get(j).toString());
            }
            showSolvedTable(letterSoup);
            

        } catch (FileNotFoundException e) {
            System.err.println("Error: Output file not found or cannot be created.");
            e.printStackTrace();
        }
    }

    public static void showSolvedTable(List<Character[]> wordSoup) {
        int soupSize = wordSoup.size();
        for (int i = 0; i < soupSize; i++) {
            for (int j = 0; j < soupSize; j++) {
                if(isPartOfWord(new Coordinate(i,j),wordSoup)) {
                    System.out.print(wordSoup.get(i)[j] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    // check coordinate of letter to  see if it is part of a word
    public static boolean isPartOfWord(Coordinate p, List<Character[]> wordSoup) {
        for (int i = 0; i < results.size(); i++) {
            Result word = results.get(i);
            int [] direction = getDirection(word.direction);
            int word_x = word.x - 1;    //  adjust index from 1-based to 0-based
            int word_y = word.y - 1;
            for (int j = 0; j < word.length; j++) {
                if (new Coordinate(word_x, word_y).equals(new Coordinate(p.x, p.y))) {
                    return true;
                } else {
                    word_x += direction[0];
                    word_y += direction[1];
                }
            }
        }
        return false;
    }
    public static int[] getDirection(String direction) {
        switch (direction) {
            case "UpLeft":
                return  new int[]{-1,-1};
            case "UpRight":
                return new int[]{-1,1};
            case "Up":
                return new int[]{-1,0};
            case "Left":
                return new int[]{0,-1};
            case "Right":
                return new int[]{0,1};
            case "DownRight":
                return new int[]{1,1};
            case "DownLeft":
                return new int[]{1,-1};
            case "Down":
                return new int[]{1,0};
            default:
                break;
        }
        return new int[]{0,0};
    }
    public static void findWords(List<Character[]> wordSoup, String word){
        int tableSize = wordSoup.size();
        int wordSize = word.length();
        word = word.toUpperCase();
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                Character letter = wordSoup.get(i)[j];
                String [] newAvailableWord = new String[2];

                if (letter != word.charAt(0)) {
                    continue;
                } else {
                    Coordinate p = new Coordinate(i, j);
                    if(checkLeft(j, wordSize)) {
                        newAvailableWord = getWordAndDirection(p,0,-1,wordSize,wordSoup);
                        if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                    }
                    if(checkRight(j, wordSize, tableSize)) {
                        newAvailableWord = getWordAndDirection(p,0,1,wordSize,wordSoup);
                        if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                    }
                    if (checkUp(i, wordSize)){
                        newAvailableWord = getWordAndDirection(p,-1,0,wordSize,wordSoup);
                        if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                        if(checkLeft(j, wordSize)) {
                            newAvailableWord = getWordAndDirection(p,-1,-1,wordSize,wordSoup);
                            if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                        }
                        if(checkRight(j, wordSize, tableSize)) {
                            newAvailableWord = getWordAndDirection(p,-1,1,wordSize,wordSoup);
                            if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                        }
                    }
                    if(checkDown(i, wordSize, tableSize)) {
                        newAvailableWord = getWordAndDirection(p,1,0,wordSize,wordSoup);
                        if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                        if (checkLeft(j, wordSize)) {
                            newAvailableWord = getWordAndDirection(p,1,-1,wordSize,wordSoup);
                            if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                        }
                        if (checkRight(j, wordSize,tableSize)) {
                            newAvailableWord = getWordAndDirection(p,1,1,wordSize,wordSoup);
                            if(wordCheck(word, newAvailableWord[0], newAvailableWord[1], p)){return;}
                        }
                    }
                }
            }
        }
        return;
    }
    public static boolean wordCheck(String word, String possible, String direction, Coordinate p) {
        if(word.equals(possible)) {
            results.add(new Result(word.toLowerCase(), word.length(), p.x+1, p.y+1, direction));
            return true;
        }
        return false;
    }
    public static boolean checkRight(int j,int w,int t) {
        if (j + w <= t) return  true;
        return false;
    }
    public static boolean checkLeft(int j,int w) {
        if (w - j <= 1) return  true;
        return false;
    }
    public static boolean checkUp(int i,int w) {
        if (i >= w - 1) return  true;
        return false;
    }
    public static boolean checkDown(int i,int w,int t) {
        if (i + w <= t) return  true;
        return false;
    }
    // i and j are first letter position, i_ and j_ are the direction of the word
    // (i-1,j-1)    (i-1,j)     (i-1,j+1)
    // (i, j-1)     (i,j)       (i,j+1)
    // (i+1,j-1)   (i+1,j)     (i+1,j+1)
    public static String[] getWordAndDirection(Coordinate p, int i_, int j_, int ws, List<Character[]> wordSoup) {
        String word = "";
        String direction = "";
        String []  result = new String [2];
        Coordinate  pos = new Coordinate(p.x,p.y);
        if (i_ == 1){
            direction += "Down";
        } else if (i_ == -1) {
            direction += "Up";
        }
        if (j_ == 1){
            direction += "Right";
        } else if (j_ == -1) {
            direction += "Left";
        }
        for (int k = 0; k < ws; k++) {
            word = word + wordSoup.get(pos.x)[pos.y];
            pos.x = pos.x + i_;
            pos.y = pos.y + j_;
        }
        result[0] = word;
        result[1] = direction;
        return result;
    }
}
