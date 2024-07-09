package lab01;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.io.File;
import java.io.FileNotFoundException;

public class WsSolver{

    static final Scanner sc = new Scanner(System.in);
    
    public static Soup checkInput(Scanner input){

        String line = input.nextLine();         //First line
        int size = line.length();               //Nº of characteres collumn & line
        int linecounter = 0;
        Soup soup = new Soup(size);
        soup.setPuzzleLine(line.toCharArray(), linecounter);

        while (input.hasNextLine()){
            line = input.nextLine();
            linecounter++;
            if(line.isBlank()){                                             //EMPTY LINES
                System.err.printf("ERROR : INVALID PUZZLE : EMPTY LINES");
                    System.exit(1);
                }
            if(line.length() != size && linecounter < size - 1) {               //PUZZLE LINES NOT THE SAME SIZE
                System.err.printf("ERROR : INVALID SIZE OF PUZZLE :\n");
                System.exit(1);
            }

            if (!line.equals(line.toUpperCase())){                              //PUZZLE NOT ALL CAPS
                if(linecounter < size - 1){
                    System.err.printf("ERROR : INVALID PUZZLE NOT ALL CAPS:");
                    System.exit(1);
                }else{                                                         //WORDLIST CANNOT BE IN ALL CAPS
                    String[] words = line.split("[,; ]");
                    for (String string : words) {
                        if(!string.matches("[a-zA-Z]+")){
                            System.err.printf("ERROR : NOT A VALID WORD :");
                            System.exit(1);             
                        }
                        soup.setWordList(string);
                    }
                }
            }
            if(linecounter < size ) soup.setPuzzleLine(line.toCharArray(), linecounter);

            
        } 
        return soup;
    }


   
    public static void main(String[] args){

        
        try {
            String file = args[0];
            Scanner input = new Scanner(new File(file));
            Soup soup = checkInput(input);
            ArrayList<String> words = soup.getWordList();
            
            for (String word : words) {
                
                ArrayList<Point> positions = soup.getCharPosition(word.charAt(0));
                for (Point position : positions){
                  
                    ArrayList<String> hits = soup.getAllPosition(position, word.length());
                    
                    for (int i = 0; i < hits.size(); i++){
                        if(hits.get(i).equals(word.toUpperCase())){
                            System.out.printf("%-15s %-7d %-10s %-10s\n", word, word.length(), position, hits.get(i +1));
                            soup.setSolvedPuzzle(position, word, hits.get(i +1));
                        }
                    }
                } 
            }
            soup.printSolvedPuzzle();

  
        } catch (FileNotFoundException e) {
            System.err.printf("ERROR %s : path ",e);
            System.exit(1);
        }
        
        
    }

}