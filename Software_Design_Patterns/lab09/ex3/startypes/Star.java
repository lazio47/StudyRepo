package startypes;

import java.awt.*;

public class Star {
    protected String description;
    protected Float[] physicalProperties;
    private int x;
    private int y;
    private StarType starType;

    public Star(int x, int y, char type) {
        this.x = x;
        this.y = y;
        starType = StarFactory.getStarType(type);
    }

    public void draw(Graphics g) {
        g.setColor(starType.getColor());
        g.fillOval(x, y , starType.getSize(), starType.getSize());
    }
    
}
