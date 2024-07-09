import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WordSearch {

    private ArrayList<String> puzzle;
    private ArrayList<String> words;

    public WordSearch(ArrayList<String> puzzle, ArrayList<String> words) {
        this.puzzle = puzzle;
        this.words = words;
    }

    public void solve() throws FileNotFoundException  {

        int fileNumber = 1;
        String fileName = "out" + fileNumber + ".txt";
        while (new File(fileName).exists()) {
            fileNumber++;
            fileName = "out" + fileNumber + ".txt";
        }
        PrintWriter writer = new PrintWriter(fileName);


       // cria uma matriz do mesmo tamanho do puzzle e preenche com "."
        int N = puzzle.size();
        char[][] matrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            String row = puzzle.get(i);
            for (int j = 0; j < N; j++) {
                matrix[i][j] = '.';
            }
        }

        // transforma as palavras em maiusculas
        for (int i = 0; i < words.size(); i++) {
            words.set(i, words.get(i).toUpperCase());
        }

        Integer countWords = 0;
        Boolean found = false;

        for (Integer w = 0; w < words.size(); w++) { // percorre as palavras
            found = false;
            for (Integer d = 0; d < N; d++){ // percorre todos os elementos da matriz por linha
                for (Integer j = 0; j < N; j++){ // percorre todos os elementos da matriz por coluna
                    if (Character.toUpperCase(words.get(w).charAt(0)) == (puzzle.get(d).charAt(j))) { // verifica se a primeira letra da palavra é igual a alguma letra da matriz
                        // verifica se a palavra cabe na horizontal para a direita
                        if (j + words.get(w).length() <= N && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d).charAt(j + k))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d][j + k] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Right");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Right");
                            }
                        }
                        // verifica se a palavra cabe na horizontal para a esquerda
                        if (j - words.get(w).length() >= -1 && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d).charAt(j - k))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d][j - k] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Left");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Left");
                            }
                        }
                        // verifica se a palavra cabe na vertical para baixo
                        if (d + words.get(w).length() <= N && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d + k).charAt(j))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d + k][j] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Down");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Down");
                            }
                        }
                        // verifica se a palavra cabe na vertical para cima
                        if (d - words.get(w).length() >= -1 && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d - k).charAt(j))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d - k ][j] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Up");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"Up");
                            }
                        }
                        // verifica se a palavra cabe na diagonal para baixo e para a direita
                        if (d + words.get(w).length() <= N && j + words.get(w).length() <= N && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d + k).charAt(j + k))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d + k][j + k] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"DownRight");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"DownRight");
                            }
                        }
                        // verifica se a palavra cabe na diagonal para baixo e para a esquerda
                        if (d + words.get(w).length() <= N && j - words.get(w).length() >= -1 && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d + k).charAt(j - k))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d + k][j - k] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"DownLeft");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"DownLeft");
                            }
                        }
                        // verifica se a palavra cabe na diagonal para cima e para a direita
                        if (d - words.get(w).length() >= -1 && j + words.get(w).length() <= N && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d - k).charAt(j + k))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d - k][j + k] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"UpRight");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"UpRight");
                            }
                        }
                        // verifica se a palavra cabe na diagonal para cima e para a esquerda
                        if (d - words.get(w).length() >= -1 && j - words.get(w).length() >= -1 && found == false) {
                            boolean match = true;
                            for (int k = 0; k < words.get(w).length(); k++) {
                                if (Character.toUpperCase(words.get(w).charAt(k)) != (puzzle.get(d - k).charAt(j - k))) {
                                    match = false;
                                    break;
                                }
                            }
                            if (match) {
                                for (int k = 0; k < words.get(w).length(); k++) {
                                    matrix[d - k][j - k] = words.get(w).charAt(k);
                                }
                                int a=d+1;
                                int b=j+1;
                                countWords++;
                                found = true;
                                String position = a + "," +  b;
                                System.out.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"UpLeft");
                                writer.printf("%-30s %-15s %-15s %-15s\n",words.get(w),words.get(w).length(),position,"UpLeft");
                            }
                        }

                        
                    }
                }
            }
        }

        if (countWords != words.size()) {
            System.out.println("Not all words were found");
            System.exit(1);
        }
        
        // nao escrever no ficheiro de texto, exit

        // escreve a matriz no ficheiro de texto 
        
        writer.write( "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                writer.write(matrix[i][j]);
            }
            writer.write("\n");
        }
        writer.close();

        // imprime a matriz no terminal
        System.out.println('\n');
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

    }
}

