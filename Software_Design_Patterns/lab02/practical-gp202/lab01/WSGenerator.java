import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class WSGenerator {
  public static void main(String[] args) {
    // check if the input is correct
    if(args.length < 4 || !args[0].equals("-i") || !args[2].equals("-s")){
      System.out.println("Usage: WSGenerator -i <filename> -s <soup_size>");
      return;
    }

    //check if input has one more arg selecting the outputfile
    if(args.length == 6 && !args[4].equals("-o")){
      System.out.println("Usage: WSGenerator -i <filename> -s <soup_size> -o <outputfile>");
      return;
    }

    String filename = args[1];
    int size = Integer.parseInt(args[3]);
    List<String> words = readFromFile(filename);

    char[][] soup = generateSoup(size, words);
    printSoup(soup);
    
    if (args.length == 6 && args[4].equals("-o")) {
      String outputFilename = args[5];
      writeToFile(outputFilename, soup);
    } else {
        writeToFile("sopa.txt", soup); // Default output file name if not specified
    }

  }

  // use to read from file
  private static List<String> readFromFile(String filename){
    List<String> wordList = new ArrayList<>();      // create a list to store the words
    try {
      File file = new File(filename);
      Scanner sc = new Scanner(file);

      // read the file line by line and add the words to the list
      while (sc.hasNextLine()) {
        String data = sc.nextLine();
        String [] words = data.split(";\\s*");     // split the line into words
        wordList.addAll(Arrays.asList(words));
      }

      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      e.printStackTrace();
    }
    return wordList;      // return the list of words
  }

  // use to write to file
  private static void writeToFile(String filename, char[][] soup){
    try {
      FileWriter writer = new FileWriter(filename);
      for(char[] row: soup){
        for(char c: row){
          writer.write(c + " ");
        }
        writer.write(" \n");
      }
      writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }

  private static char[][] generateSoup(int size, List<String> words) {
    char[][] soup = new char[size][size];
    Random random = new Random();
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            soup[i][j] = (char) ('A' + random.nextInt(26));
        }
    }

    // insert words horizontal, vertical, or diagonal
    for (String word : words) {
      boolean wordInserted = false;
      int attempts = 0;
      while (!wordInserted && attempts < size * size) { // limit attempt
          int row = random.nextInt(size);
          int col = random.nextInt(size);
          int direction = random.nextInt(8);
          if (canFit(soup, word, row, col, direction)) {
              insertWord(soup, word, row, col, direction);
              wordInserted = true;
          }
          attempts++;
      }
      if (!wordInserted) {
          System.out.println("Could not insert word: " + word);
      }
  }
  return soup;
}

private static boolean canFit(char[][] soup, String word, int row, int col, int direction) {
  int size = soup.length;
  int len = word.length();

  switch (direction) {
      case 0: // horizontal leftToRight
          if (col + len <= size) {
              for (int i = 0; i < len; i++) {
                  if (soup[row][col + i] != 0 && soup[row][col + i] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 1: // horizontal RightToLeft
          if (col - len >= -1) {
              for (int i = 0; i < len; i++) {
                  if (soup[row][col - i] != 0 && soup[row][col - i] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 2: // vertical UpToDown
          if (row + len <= size) {
              for (int i = 0; i < len; i++) {
                  if (soup[row + i][col] != 0 && soup[row + i][col] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 3: // vertical downToUp
          if (row - len >= -1) {
              for (int i = 0; i < len; i++) {
                  if (soup[row - i][col] != 0 && soup[row - i][col] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 4: // diagonal upToLeft
          if (row - len >= -1 && col - len >= -1) {
              for (int i = 0; i < len; i++) {
                  if (soup[row - i][col - i] != 0 && soup[row - i][col - i] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 5: // diagonal upToRight
          if (row - len >= -1 && col + len <= size) {
              for (int i = 0; i < len; i++) {
                  if (soup[row - i][col + i] != 0 && soup[row - i][col + i] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 6: // diagonal downToLeft
          if (row + len <= size && col - len >= -1) {
              for (int i = 0; i < len; i++) {
                  if (soup[row + i][col - i] != 0 && soup[row + i][col - i] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;

      case 7: // diagonal downToRight
          if (row + len <= size && col + len <= size) {
              for (int i = 0; i < len; i++) {
                  if (soup[row + i][col + i] != 0 && soup[row + i][col + i] != word.charAt(i)) {
                      return false;
                  }
              }
              return true;
          }
          break;
  }
  return false;
}

private static void insertWord(char[][] soup, String word, int row, int col, int direction) {
  int len = word.length();

  switch (direction) {
      case 0: // horizontal leftToRight
          for (int i = 0; i < len; i++) {
              soup[row][col + i] = word.charAt(i);
          }
          break;

      case 1: // horizontal RightToLeft
          for (int i = 0; i < len; i++) {
              soup[row][col - i] = word.charAt(i);
          }
          break;

      case 2: // vertical UpToDown
          for (int i = 0; i < len; i++) {
              soup[row + i][col] = word.charAt(i);
          }
          break;

      case 3: // vertical downToUp
          for (int i = 0; i < len; i++) {
              soup[row - i][col] = word.charAt(i);
          }
          break;

      case 4: // diagonal upToLeft
          for (int i = 0; i < len; i++) {
              soup[row - i][col - i] = word.charAt(i);
          }
          break;

      case 5: // diagonal upToRight
          for (int i = 0; i < len; i++) {
              soup[row - i][col + i] = word.charAt(i);
          }
          break;

      case 6: // diagonal downToLeft
          for (int i = 0; i < len; i++) {
              soup[row + i][col - i] = word.charAt(i);
          }
          break;

      case 7: // diagonal downToRight
          for (int i = 0; i < len; i++) {
              soup[row + i][col + i] = word.charAt(i);
          }
          break;
  }

}

  private static void printSoup(char[][] soup) {
    for (char[] row : soup) {
        for (char c : row) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
  }

}