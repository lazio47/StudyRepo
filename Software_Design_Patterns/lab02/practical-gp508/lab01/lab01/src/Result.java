public class Result {
    String word;
    int length;
    int x;
    int y;
    String direction;
    Result(String word, int length, int x, int y, String direction) {
        this.word = word;
        this.length = length;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    @Override
    public String toString() {
        return ""+ this.word + "\t" + this.length + "\t" + this.x + "," + this.y + "\t" + this.direction +"\n";
    }

}
