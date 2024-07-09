//package practical-gp202.lab01;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WSSolver {
    public static void main(String[] args){
    
        String file_name = args[0];
        List<String> keyWords = new ArrayList<String>();
        List<Character> puzzle = new ArrayList<Character>();
        
        try{
        File file = new File(file_name);
        Scanner sc = new Scanner(file);
        int count=0;

        //file reading
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            if (Character.isLowerCase(line.charAt(0)))
            {
                String[] words = line.split("[, ;]+");
                for (String s : words)
                {keyWords.add(s);}
            }
            else{  
                char[] chars = line.toCharArray();
                for (Character c : chars){
                    puzzle.add(count++,c);
                }
            }
        }
        sc.close();

        if (!isWSSquare(puzzle))
        {System.err.println("Erro: Puzzle nao quadrado");
        return;}

        }
        catch (FileNotFoundException e)
        {   
            System.err.println("Erro ao abrir ficheiro");
            e.printStackTrace();
        }

        //transformar a lista de chars em um array bi-dim
        int count=0;
        int dim = (int)Math.sqrt(puzzle.size());
        char[][] words_soup = new char[dim][dim];
        for(int i =0; i<dim; i++){
            for (int j=0; j<dim; j++){
                words_soup[j][i] = puzzle.get(count++); 
            }
        }
        
        printList(words_soup, keyWords);
        printSolvedWS(words_soup, keyWords);
        
    }


    public static void printSolvedWS(char[][] puzzle, List<String> keyWords)
    {
        char[][] solvedPuzzle = new char[puzzle.length][puzzle.length];

        // fill the array with dots
        for (int i = 0; i < puzzle.length; i++)
            {for (int j = 0; j < puzzle.length; j++)
                {solvedPuzzle[i][j] = '.';}
        }

        // iterate over each word
        for (String  word : keyWords)
        {
            int[] coords_dir = finder(puzzle, word);
            if (coords_dir != null)
            {
                int x = coords_dir[0] - 1;
                int y = coords_dir[1] - 1;
                int dir_x = coords_dir[2];
                int dir_y = coords_dir[3];

                // fill the solvedPuzzle with the chars from the keyWords
                for (int i = 0; i < word.length(); i++) {
                    solvedPuzzle[y][x] = Character.toUpperCase(word.charAt(i));
                    x += dir_x;
                    y += dir_y;
                }
            }
            else{System.err.println("Erro: funçao finder a retornar null");}
        }

        for (int i = 0; i < solvedPuzzle.length; i++)
        {
            System.out.println();
            for (int j = 0; j < solvedPuzzle.length; j++)
                {
                    System.out.printf(" %c ", solvedPuzzle[i][j]);
                }
        }
    }

    //Checks size and square
    public static boolean isWSSquare(List<Character> puzzle){
        assert(puzzle.size() <= 40);
        double square_root = Math.sqrt(puzzle.size());

        if ((Math.pow(Math.round(square_root),2) == puzzle.size()))
        {return true;}
        else
        {return false;}
    }

    private static int[][] directions = 
    {
        {0,1},      // Up
        {0,-1},     // Down
        {1,0},      // Rigth
        {-1,0},     // Left
        {1,1},      // UpRight
        {-1,1},     // UpLeft
        {1,-1},     // DownRigth
        {-1,-1}     // DownLeft
    
    };

    public static String coordsToDir(int x, int y)
    {
        assert((x == 0 || x == 1 || x == -1) && (y == 0 || y == 1 || y == -1));
        String dir = "";
        if (y == -1)
        {dir += "Up";}
        else if (y == 1)
        {dir += "Down";}

        if (x == 1)
        {dir += "Rigth";}
        else if (x == -1)
        {dir += "Left";}

        return dir;
    } 

    public static void printList(char[][] puzzle, List<String> keyWords)
    {
        //para cada word
        for (int i = 0; i < keyWords.size(); i++)
        {
            int[] coords_dir = finder(puzzle, keyWords.get(i));
            if (coords_dir != null)
            {
                String dir_str = coordsToDir(coords_dir[2], coords_dir[3]);

                System.out.printf("%-15s %-5d %d,%-5d %-15s", keyWords.get(i), (keyWords.get(i)).length(), coords_dir[1], coords_dir[0], dir_str);
                System.out.println();
            }
            else{System.err.println("Erro: funçao finder a retornar null");}
        }
    }

    public static int[] finder(char[][] puzzle, String word)
    {
        //Percorrer o array char por char
        for (int i = 0; i < puzzle.length; i++){
            for (int j = 0; j < puzzle.length; j ++)
            {                
                //Verificar se o 1ºchar corresponde ao 1º da word
                if (puzzle[j][i] == Character.toUpperCase(word.charAt(0)))
                {
                    //Verificar todas as direcções
                    for (int[] dir : directions)
                    {
                        // pos(x, y), pos(x+dx,y+dy)
                        int dx = dir[0];    int dy = dir[1];
                        int x = j;          int y = i;
                        int count=0;

                        //Iterar pelo percurso (possivel) da word
                        for (int k = 1; k < word.length(); k++)
                        {
                            //Atualizar as coords da procura do char
                            x += dx;
                            y += dy;

                            // validação das novas coords
                            if ((x >= puzzle.length) || (x < 0) || (y >= puzzle[0].length) || (y < 0))
                            {break;}
                            
                            if (puzzle[x][y] == Character.toUpperCase(word.charAt(k)))
                            {count++;}
                        }

                        //verificar se todas as letras da word são iguais
                        if (count == word.length()-1){
                            int[] coords_dir = {j+1, i+1, dir[0], dir[1]};
                            return coords_dir;             
                        }
                    }
                }
            }
        }
        // coords_dir = [x, y, dir(x), dir(y)]
        return null;
    }
}