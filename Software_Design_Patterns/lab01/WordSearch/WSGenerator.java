import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.exit;

public class WSGenerator {

    private static final int MAX_TENTATIVAS = 100;
    public static void main(String[] args) throws IOException{
        if (args.length < 2 ) {
            System.out.println("Uso: java WSGenerator -i <arquivo_palavras> -s <dimensao_sopa> [-o <arquivo_saida>]");
            return;
        }

        String inputFile = "";
        String outputFile = "sopa.txt"; // Valor padrão para o arquivo de saída "sopa.txt
        int sopaSize = 0;
        
        

        for (int i = 0; i < args.length; i++) {
            if ("-i".equals(args[i]) && i + 1 < args.length) {
                inputFile = args[i + 1];
            } 
            if ("-o".equals(args[i]) && i + 1 < args.length) {
                outputFile = args[i + 1];
            } 
            if ("-s".equals(args[i]) && i + 1 < args.length) {
                try {
                    sopaSize = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.out.println("A dimensão da sopa deve ser um número inteiro.");
                    return;
                }
            }
        }

        if (inputFile.isEmpty() || sopaSize <= 0) {
            System.out.println("Argumentos inválidos. Dimensão inváliad.");
            return;
        }

        List < String > palavras = lerPalavras(inputFile);
        if (palavras.isEmpty()) {
            System.out.println("Não foram encontradas palavras válidas no arquivo de entrada.");
            return;
        }

        char[][] sopaLetras = gerarSopa(palavras, sopaSize);
        
        if (sopaLetras != null) {
            salvarSopa(sopaLetras, outputFile, palavras);
        } else {
            System.out.println("Não foi possível gerar a Sopa de Letras.");
        }


        for (int i = 1; i < MAX_TENTATIVAS; i++) {
            new WordSearchSolver(outputFile);
            System.out.println("Tentativa " + i);
            if (WordSearchSolver.validadePuzzle == true) {
                System.out.println("Sopa de Letras válida!");
                System.out.println("Sopa de Letras gerada com sucesso!");
                exit(0);
                break;
            } else {
                sopaLetras = gerarSopa(palavras, sopaSize);
        
                if (sopaLetras != null) {
                    salvarSopa(sopaLetras, outputFile, palavras);
                } else {
                    System.out.println("Não foi possível gerar a Sopa de Letras.");
                }
            }
        }
        System.out.println("Não foi possível gerar uma sopa de letras válida após 100 tentativas.");
        
    }


    private static List < String > lerPalavras(String inputFile) {
        List < String > palavras = new ArrayList < > ();
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (!linha.trim().isEmpty()) {
                    Collections.addAll(palavras, linha.split("\\s+"));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de palavras: " + e.getMessage());
        }
        return palavras;
    }

    private static char[][] gerarSopa(List<String> palavras, int sopaSize) {
        int tentativas = 0;
            int maxPalavra = 0;

            for (String palavra : palavras) {
                if (palavra.length() > maxPalavra) {
                    maxPalavra = palavra.length();
                }
            }

            if (sopaSize < maxPalavra) {
                System.err.println("A dimensão da sopa de letras é muito pequena para a maior palavra.");
                return null;
            }

            char[][] sopaLetras = new char[sopaSize][sopaSize];

            for (int i = 0; i < palavras.size(); i++) {
                palavras.set(i, palavras.get(i).toUpperCase());
            }

            for (String palavra : palavras) {
                int direction = new Random().nextInt(8);
                int wordSize = palavra.length();

                int x = 0;
                int y = 0;

                boolean inserted = false;
                while (!inserted && tentativas < 1000) {
                    switch(direction) {
                        //left to right
                        case 0:
                            if(sopaSize-wordSize > 0)
                                x = new Random().nextInt(sopaSize - wordSize);
                            y = new Random().nextInt(sopaSize);
                            break;
                        //right to left
                        case 1:
    
                            if(sopaSize-wordSize > 0)
                                x = new Random().nextInt(sopaSize - wordSize) + wordSize;
                            else
                                x = wordSize-1;
                            y = new Random().nextInt(sopaSize);
                            break;
                        //down to up
                        case 2:
                            x = new Random().nextInt(sopaSize);
                            if (sopaSize-wordSize > 0)
                                y = new Random().nextInt(sopaSize - wordSize);
                            else
                                y = wordSize-1;
                            break;
                        //up to down
                        case 3:
                            x = new Random().nextInt(sopaSize);
                            if (sopaSize-wordSize > 0)
                                y = new Random().nextInt(sopaSize - wordSize) + wordSize;
                            else
                                y = wordSize-1;
                            break;
                        //right-up
                        case 4:
                            if (sopaSize-wordSize > 0) {
                                x = new Random().nextInt(sopaSize - wordSize);
                                y = new Random().nextInt(sopaSize - wordSize) + wordSize;
                            }
                            else {
                                x = 0;
                                y = wordSize-1;
                            }
                            break;
                        //right-down
                        case 5:
                            if (sopaSize-wordSize > 0) {
                                x = new Random().nextInt(sopaSize - wordSize);
                                y = new Random().nextInt(sopaSize - wordSize);
                            }
                            else {
                                x = 0;
                                y = 0;
                            }
                            break;
                        //left-up
                        case 6:
                            if (sopaSize-wordSize > 0) {
                                x = new Random().nextInt(sopaSize - wordSize) + wordSize;
                                y = new Random().nextInt(sopaSize - wordSize) + wordSize;
                            }
                            else {
                                x = wordSize-1;
                                y = wordSize-1;
                            }
                            break;
                        //left-down
                        case 7:
                            if (sopaSize-wordSize > 0) {
                                x = new Random().nextInt(sopaSize - wordSize) + wordSize;
                                y = new Random().nextInt(sopaSize - wordSize);
                            }
                            else {
                                x = wordSize-1;
                                y = 0;
                            }
                            break;
                    }

                    if (canInsert(sopaLetras, x, y, direction, palavra)) {
                        insertWord(sopaLetras, palavra, x, y, direction);
                        inserted = true;
                    }
                    else {
                        tentativas++;
                    }
                }
            }

            // estatística i guess
            if (tentativas >= 1000) {
                System.err.println("Não foi possível inserir todas as palavras na sopa de letras após 1000 tentativas.");
            }

            for (int i = 0; i < sopaSize; i++) {
                for (int j = 0; j < sopaSize; j++) {
                    if (sopaLetras[i][j] == '\0') {
                        sopaLetras[i][j] = (char) (new Random().nextInt(26) + 'A');
                    }
                }
            }
            return sopaLetras;
        }



    private static void insertWord(char[][] sopaLetras, String palavra, int x, int y, int direction) {
        // 0 - right 1 - left 2 - up 3 - down 4 - right-up 5 - right-down 6 - left-up 7 - left-down
        switch(direction) {
            //left to right
            case 0:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x + i][y] = palavra.charAt(i);
                }
                break;
            //right to left
            case 1:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x - i][y] = palavra.charAt(i);
                }
                break;
            //down to up
            case 2:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x][y + i] = palavra.charAt(i);
                }
                break;
            //up to down
            case 3:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x][y - i] = palavra.charAt(i);
                }
                break;
            //right-up
            case 4:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x + i][y - i] = palavra.charAt(i);
                }
                break;
            //right-down
            case 5:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x + i][y + i] = palavra.charAt(i);
                }
                break;
            //left-up
            case 6:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x - i][y - i] = palavra.charAt(i);
                }
                break;
            //left-down
            case 7:
                for (int i = 0; i < palavra.length(); i++) {
                    sopaLetras[x - i][y + i] = palavra.charAt(i);
                }
                break;
        }
    }


    private static boolean canInsert(char[][] sopaLetras, int x, int y, int direction, String palavra) {
        //check if the word can be inserted in the chosen direction
        // 0 - right 1 - left 2 - up 3 - down 4 - right-up 5 - right-down 6 - left-up 7 - left-down
        int wordSize = palavra.length();

        switch(direction) {
            //left to right
            case 0:
                for (int i = 0; i < wordSize - 1; i++) {
                    if (sopaLetras[x + i][y] != '\0') {
                        return false;
                    }
                }
                break;
            //right to left
            case 1:
                for (int i = 0; i < wordSize ; i++) {
                    if (sopaLetras[x - i][y] != '\0') {
                        return false;
                    }
                }
                break;
            //down to up
            case 2:
                for (int i = 0; i < wordSize; i++) {
                    if (sopaLetras[x][y + i] != '\0') {
                        return false;
                    }
                }
                break;
            //up to down
            case 3:
                for (int i = 0; i < wordSize - 1; i++) {
                    if (sopaLetras[x][y - i] != '\0') {
                        return false;
                    }
                }
                break;
            //right-up
            case 4:
                for (int i = 0; i < wordSize - 1; i++) {
                    if (sopaLetras[x + i][y - i] != '\0') {
                        return false;
                    }
                }
                break;
            //right-down
            case 5:
                for (int i = 0; i < wordSize - 1; i++) {
                    if (sopaLetras[x + i][y + i] != '\0') {
                        return false;
                    }
                }
                break;
            //left-up
            case 6:
                for (int i = 0; i < wordSize - 1; i++) {
                    if (sopaLetras[x - i][y - i] != '\0') {
                        return false;
                    }
                }
                break;
            //left-down
            case 7:
                for (int i = 0; i < wordSize - 1; i++) {
                    if (sopaLetras[x - i][y + i] != '\0') {
                        return false;
                    }
                }
                break;
        }
        return true;
    }

    private static void imprimirSopa(char[][] sopaLetras) {
        for (int i = 0; i < sopaLetras.length; i++) {
            for (int j = 0; j < sopaLetras[i].length; j++) {
                System.out.print(sopaLetras[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void salvarSopa(char[][] sopaLetras, String outputFile, List < String > palavrasUsadas) {
    try (FileWriter writer = new FileWriter(outputFile)) {
        for (char[] linha : sopaLetras) {
            for (char letra : linha) {
                writer.write(letra);
            }
            writer.write('\n');
        }
        for (int i = 0; i < palavrasUsadas.size(); i++) {
            palavrasUsadas.set(i, palavrasUsadas.get(i).toLowerCase());
        }
        writer.write("" + String.join("; ", palavrasUsadas));
    } catch (IOException e) {
        System.out.println("Erro ao salvar a Sopa de Letras no arquivo: " + e.getMessage());
    }
}

}