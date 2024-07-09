package lab01;

import java.util.ArrayList;

class Point {
    int collumn;
    int row;
    public Point(int row, int collumn){
        this.collumn = collumn;
        this.row = row;
    }
    public int getCollumn(){ return this.collumn;}

    public int getRow(){ return this.row;}

    @Override
    public String toString() {
        return "(" + this.row + "," + this.collumn + ")" ;
    }
}

public class Soup {

    private char[][] puzzle;
    private ArrayList<String> wordlist;
    int size;
    Point point;
    private char[][] puzzleSolved;


    public Soup(int size){
        
        this.puzzle = new char[size][size];
        this.size = size;
        this.wordlist = new ArrayList<>();
        this.puzzleSolved = new char[size][size];
        for (int i = 0; i < size; i++) {
            for(int j = 0; j< size; j++){
                this.puzzleSolved[i][j] = '.';
            }
        }
    }

    public void setPuzzleLine(char[] line, int linenumber){
        
        for(int i = 0; i < size; i++){
            this.puzzle[linenumber][i] = line[i];
        }
    }

    public void setWordList(String word){
        this.wordlist.add(word);
    }

    public void setWordList(String[] wordList){
        for (String word : wordList) {
            this.wordlist.add(word);
        }
    }

    public ArrayList<String> getWordList(){ return this.wordlist;}

    public String getLine(int line){

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.size; i++){
            result.append(this.puzzle[line][i]);
        }
        return result.toString();
    }


    public String getCollumn(int collumn){

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.size; i++){
            result.append(this.puzzle[i][collumn]);
        }
        return result.toString();
    }

    public String getUpLeft(Point point){

        StringBuilder result = new StringBuilder();
        for (int i = point.row, k = point.collumn; i >= 0 && k >=0; i--,k--) {
            result.append(this.puzzle[i][k]);
        }
        return result.toString();
    }


    public String getDownLeft(Point point){

        StringBuilder result = new StringBuilder();
        for (int i = point.row, k = point.collumn; i < this.size && k >=0; i++, k--) {
            result.append(this.puzzle[i][k]);
            
        }
        return result.toString();
    }


    public String getUpRight(Point point){

        StringBuilder result = new StringBuilder();
        for (int i = point.row, k = point.collumn; i >= 0 && k < this.size; i--, k++) {
            result.append(this.puzzle[i][k]);
        }
        return result.toString();
    }


    public String getDownRight(Point point){

        StringBuilder result = new StringBuilder();
        for (int i = point.row,k = point.collumn; i < this.size && k < this.size; i++,k++) {
            result.append(this.puzzle[i][k]);
        }
        return result.toString();
    }

    public ArrayList<Point> getCharPosition(char character){

        ArrayList<Point> result = new ArrayList<Point>() ;
        character = Character.toUpperCase(character);
        for(int i=0; i < this.size; i++){
            for(int k=0; k < this.size; k++){
                if (this.puzzle[i][k] == character){
                    Point point = new Point(i, k);
                    result.add(point);
                }
            }
        }
        return result;
    }

    public ArrayList<String> getCharLine(Point point, int wordlength){

        int row = point.row;
        String line = getLine(row);
        ArrayList<String> result = new ArrayList<>();
       
        if(point.collumn - wordlength < 0 && point.collumn + wordlength > this.size ){
            return result;
        }

        if(point.collumn < wordlength - 1){

            result.add(line.substring(point.collumn, point.collumn + wordlength));
            result.add("RIGHT");
        }
    
        if(point.collumn + wordlength > this.size){
            StringBuilder aux = new StringBuilder(line.substring(point.collumn - wordlength + 1 , point.collumn + 1));
            result.add(aux.reverse().toString());
            result.add("LEFT");
        }
    
        if(point.collumn >= wordlength - 1 && point.collumn + wordlength <= this.size){
            result.add(line.substring(point.collumn, point.collumn + wordlength));
            result.add("RIGHT");
            StringBuilder aux = new StringBuilder(line.substring(point.collumn - wordlength + 1 , point.collumn + 1));
            result.add(aux.reverse().toString());
            result.add("LEFT");
        }
    
        return result;
    
    }

    public ArrayList<String> getCharCollumn(Point point, int wordlength){

        int collumn = point.collumn;
        String line = getCollumn(collumn);
        ArrayList<String> result = new ArrayList<>();

        if(point.row < wordlength - 1 && point.row + wordlength > this.size ){
            return result;
        }

        if(point.row < wordlength - 1){
            result.add(line.substring(point.row, point.row + wordlength));
            result.add("DOWN");
        }
    
        if(point.row + wordlength > this.size){
            StringBuilder aux = new StringBuilder(line.substring(point.row - wordlength + 1 , point.row + 1));
            result.add(aux.reverse().toString());
            result.add("UP");
        }
    
        if(point.row >= wordlength - 1 && point.row + wordlength <= this.size){
            result.add(line.substring(point.row, point.row + wordlength));
            result.add("DOWN");
            StringBuilder aux = new StringBuilder(line.substring(point.row - wordlength + 1 , point.row + 1));
            result.add(aux.reverse().toString());
            result.add("UP");
        }
        return result;
    }

    public ArrayList<String> getDiagonals(Point point, int wordlength){
        ArrayList<String> result = new ArrayList<>();
        String upLeft = getUpLeft(point);
        if (upLeft.length() > wordlength){
            result.add(upLeft.substring(0, wordlength));
            result.add("UPLEFT");
        }
        String downLeft = getDownLeft(point);
        if (downLeft.length() > wordlength){
            result.add(downLeft.substring(0, wordlength));
            result.add("DOWNLEFT");
        }
        String upRight = getUpRight(point);
        if (upRight.length() > wordlength){
            result.add(upRight.substring(0, wordlength));
            result.add("UPRIGHT");
        }
        String downRight = getDownRight(point);
        if (downRight.length() > wordlength){
            result.add(downRight.substring(0, wordlength));
            result.add("DOWNRIGHT");
        }

        return result;

    }
    public ArrayList<String> getAllPosition(Point point, int wordlength){
        ArrayList<String> result = new ArrayList<>();
        result.addAll(getCharLine(point, wordlength));
        result.addAll(getCharCollumn(point, wordlength));
        result.addAll(getDiagonals(point, wordlength));
        return result;
    }
    
    public void setSolvedPuzzle(Point point, String word, String direction){

        switch (direction) {
            case "UP":
                for (int i = point.row, j=0; i > point.row - word.length(); i--,j++) {
                    this.puzzleSolved[i][point.collumn] = word.charAt(j);
                }
                break;
            case "DOWN":
            for (int i = point.row, j=0; i < point.row + word.length(); i++,j++) {
                this.puzzleSolved[i][point.collumn] = word.charAt(j);
                }
                break;
            case "LEFT":
                for (int i = point.collumn, j=0; i > point.collumn - word.length(); i--,j++) {
                    this.puzzleSolved[point.row][i] = word.charAt(j);
                }
                break;
            case "RIGHT":
                for (int i = point.collumn, j=0; i < point.collumn + word.length(); i++,j++) {
                    this.puzzleSolved[point.row][i] = word.charAt(j);
                }
                break;
            case "UPLEFT":
                for (int i = point.row, k = point.collumn,j=0; i >= 0 && k >=0 && j < word.length(); i--,k--,j++) {
                    this.puzzleSolved[i][k] = word.charAt(j);
                }
                break;
            case "UPRIGHT":
                for (int i = point.row, k = point.collumn,j=0; i >= 0 && k < this.size && j < word.length(); i--, k++,j++) {
                    this.puzzleSolved[i][k] = word.charAt(j);
                }
                break;
            case "DOWNLEFT":
                for (int i = point.row, k = point.collumn,j=0; i < this.size && k >=0 && j < word.length(); i++, k--,j++) {
                    this.puzzleSolved[i][k] = word.charAt(j);
                    
                }
                break;
            case "DOWNRIGHT":
                for (int i = point.row,k = point.collumn,j=0; i < this.size && k < this.size && j < word.length(); i++,k++,j++) {
                    this.puzzleSolved[i][k] = word.charAt(j);
                }
                break;
            default:
                break;
        }

    }
    public void printSolvedPuzzle(){
        for (int i = 0; i < size; i++) {
            for(int j = 0; j< size; j++){
                System.out.print(this.puzzleSolved[i][j]);
            }
            System.out.println();
        }
    }

}