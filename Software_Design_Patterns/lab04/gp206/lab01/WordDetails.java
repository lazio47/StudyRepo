package lab01;

public class WordDetails {
    private String word;
    private int size;
    private int row;
    private int col;
    private String direction;

    public WordDetails(String word) {
        this.word = word.toUpperCase(); 
        this.size = word.length();
        this.row = -1; 
        this.col = -1; 
        this.direction = null; 
    }

    public WordDetails(String word, int size, int row, int col, String direction) {
        this.word = word;
        this.size = size;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
    
    public String getWord() {
        return word;
    }

    public int getSize() {
        return size;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getDirection() {
        return direction;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d,%d %s", word, size, row, col, direction);
    }
}
