package startypes;

import java.awt.Color;

public class ColorSize {
    private Color color;
    private int size;
    public ColorSize(Color color, int size) {
        this.color = color;
        this.size = size;
    }
    public Color getColor() {
        return color;
    }
    public int getSize() {
        return size;
    }
    
}
