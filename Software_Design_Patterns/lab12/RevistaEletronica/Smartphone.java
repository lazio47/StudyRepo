public class Smartphone {
    private int memory;
    private double price;
    private String description;

    public Smartphone(int memory, double price, String description) {
        this.memory = memory;
        this.price = price;
        this.description = description;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAtributo(String atributo) {
        switch (atributo) {
            case "memory":
                return memory;
            case "price":
                return price;
            case "description":
                return description;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "RAM: " + memory + "GB, Price: $" + price + ", Description: " + description;
    }
}
