package startypes;
import java.awt.*;

public abstract class StarType {
    private int size;
    private Color color;
    protected String description;
    protected Float[] physicalProperties;

    public StarType(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float[] getPhysicalProperties() {
        return physicalProperties;
    }

    public void setPhysicalProperties(Float[] physicalProperties) {
        this.physicalProperties = physicalProperties;
    }
    
}
