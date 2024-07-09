import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WSGenerator {
    public static void main(String[] args) throws IOException {

        int argIndex = 0;
        int puzzleSize = 0;
        String inputFile = "";
        String outputFile = "";
        String direction = "";

        if (args.length == 0) {
            System.out.println("Error: no arguments");
            System.exit(0);
        }

        for (String arg : args) {
            switch (arg) {
                case "-i":
                    argIndex++;
                    inputFile = args[argIndex];
                    break;
                case "-s":
                    argIndex++;
                    puzzleSize = Integer.parseInt(args[argIndex]);
                    break;
                case "-o":
                    argIndex++;
                    outputFile = args[argIndex];
                    break;
                default:
                    argIndex++;
                    break;
            }
        }

        // tamanho da sopa
        if (puzzleSize <= 0 || puzzleSize > 40) {
            System.out.println("Error: Puzzle size must be between 1 and 40");
            System.exit(1);
        }

        Path path = Paths.get(inputFile);
        ArrayList<String> wordsList = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(path);

        for (String line : fileLines) {
            String[] lineWords = line.split("[,;\\s]+");
            wordsList.addAll(Arrays.asList(lineWords));
        }

        // letras maisculas
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[][] puzzleTable = new char[puzzleSize][puzzleSize];

        // palavras na sopa de letras
        for (String word : wordsList) {
            int attempts = 0;
            while (true) {
                attempts++;
                String[] directions = {"up", "down", "left", "right", "upleft", "upright", "downleft", "downright"};
                direction = directions[new Random().nextInt(directions.length)];
                
                int[] initialPosition = getInitialPosition(puzzleSize, word.length(), direction);
                if (isWordPlacementValid(word.toCharArray(), initialPosition, puzzleTable, direction)) { // verifica se a palavra pode ser colocada na sopa de letras
                    puzzleTable = addWordToTable(word.toCharArray(), initialPosition, puzzleTable, direction); // adiciona a palavra à sopa de letras
                    break;
                }
                if (attempts == 10000) {
                    System.out.println("Error: Could not add word " + word + " to the table after 10000 attempts");
                    System.exit(1);
                }
            }
        }

        Random random = new Random();

        // se nao houver arquivo de saída, escrever no terminal (caso o nome do arquivo de saida seja "")
        if (outputFile.equals("")) {
            for (int y = 0; y < puzzleSize; y++) {
                for (int x = 0; x < puzzleSize; x++) {
                    char letter = Character.toUpperCase(puzzleTable[y][x]);

                    if (letter == '\u0000') {
                        letter = Character.toUpperCase(alphabet[random.nextInt(alphabet.length)]);
                    }

                    System.out.print(letter);
                }
                System.out.println();
            }

            for (String word : wordsList) {
                System.out.print(word + " ");
            }
            System.out.println();
            System.exit(0);
        }

        // escreve no ficheiro
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int y = 0; y < puzzleSize; y++) {
                for (int x = 0; x < puzzleSize; x++) {
                    char letter = Character.toUpperCase(puzzleTable[y][x]);

                    if (letter == '\u0000') {
                        letter = Character.toUpperCase(alphabet[random.nextInt(alphabet.length)]);
                    }

                    writer.write(letter);
                }
                writer.write("\n");
            }

            // lista de palavras (cada palavra separada por um espaço)
            for (String word : wordsList) {
                writer.write(word + " ");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to write word soup to file", e);
        }
    }

    private static int[] getInitialPosition(int puzzleSize, int wordSize, String direction) { 
        int[] initialPosition = new int[2];
        Random newRandom = new Random();
        switch (direction) {
            case "up":
                initialPosition[0] = newRandom.nextInt(puzzleSize);
                initialPosition[1] = newRandom.nextInt(puzzleSize - wordSize) + wordSize;
                break;
            case "down":
                initialPosition[0] = newRandom.nextInt(puzzleSize);
                initialPosition[1] = newRandom.nextInt(puzzleSize - wordSize);
                break;
            case "left":
                initialPosition[0] = newRandom.nextInt(puzzleSize - wordSize) + wordSize;
                initialPosition[1] = newRandom.nextInt(puzzleSize);
                break;
            case "right":
                initialPosition[0] = newRandom.nextInt(puzzleSize - wordSize);
                initialPosition[1] = newRandom.nextInt(puzzleSize);
                break;
            case "upleft":
                initialPosition[0] = newRandom.nextInt(puzzleSize - wordSize) + wordSize;
                initialPosition[1] = newRandom.nextInt(puzzleSize - wordSize) + wordSize;
                break;
            case "upright":
                initialPosition[1] = newRandom.nextInt(puzzleSize - wordSize) + wordSize;
                initialPosition[0] = newRandom.nextInt(puzzleSize - wordSize);
                break;
            case "downleft":
                initialPosition[1] = newRandom.nextInt(puzzleSize - wordSize);
                initialPosition[0] = newRandom.nextInt(puzzleSize - wordSize) + wordSize;
                break;
            case "downright":
                initialPosition[0] = newRandom.nextInt(puzzleSize - wordSize);
                initialPosition[1] = newRandom.nextInt(puzzleSize - wordSize);
                break;
        }
        return initialPosition;
    }

    private static boolean isWordPlacementValid(char[] word, int[] initialPosition, char[][] puzzleTable, String direction) {
        int yChange = 0;
        int xChange = 0;

        switch (direction) {
            case "up":
                yChange = -1;
                break;
            case "down":
                yChange = 1;
                break;
            case "left":
                xChange = -1;
                break;
            case "right":
                xChange = 1;
                break;
            case "upleft":
                yChange = -1;
                xChange = -1;
                break;
            case "upright":
                yChange = -1;
                xChange = 1;
                break;
            case "downleft":
                yChange = 1;
                xChange = -1;
                break;
            case "downright":
                yChange = 1;
                xChange = 1;
                break;
            default:
                return false;
        }

        for (int i = 0; i < word.length; i++) {
            int y = initialPosition[1] + (i * yChange);
            int x = initialPosition[0] + (i * xChange);
            if (y < 0 || y >= puzzleTable.length || x < 0 || x >= puzzleTable[y].length || puzzleTable[y][x] != '\u0000' && puzzleTable[y][x] != word[i]) {
                return false;
            }
        }
        return true;
    }

    private static char[][] addWordToTable(char[] word, int[] initialPosition, char[][] puzzleTable, String direction) {
        switch (direction) {
            case "up":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1] - i][initialPosition[0]] = word[i];
                }
                break;
            case "down":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1] + i][initialPosition[0]] = word[i];
                }
                break;
            case "left":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1]][initialPosition[0] - i] = word[i];
                }
                break;
            case "right":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1]][initialPosition[0] + i] = word[i];
                }
                break;
            case "upleft":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1] - i][initialPosition[0] - i] = word[i];
                }
                break;
            case "upright":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1] - i][initialPosition[0] + i] = word[i];
                }
                break;
            case "downleft":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1] + i][initialPosition[0] - i] = word[i];
                }
                break;
            case "downright":
                for (int i = 0; i < word.length; i++) {
                    puzzleTable[initialPosition[1] + i][initialPosition[0] + i] = word[i];
                }
                break;
        }
        return puzzleTable;
    }
}
