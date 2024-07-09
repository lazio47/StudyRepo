public class Cake {

    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;
    
    public Cake(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public void setCakeShape(Shape shape) {
        this.shape = shape;
    }

    public void addCakeLayer() {
        this.numCakeLayers++;
    }
    
    public void addCreamLayer(Cream cream) {
        if (this.numCakeLayers == 1) {
            this.midLayerCream = cream;
        } else if (this.numCakeLayers == 2 || this.numCakeLayers == 3) {
            this.midLayerCream = cream;
        }
    }

    public void addTopLayerCream(Cream cream) {
        this.topLayerCream = cream;
    }

    public void addMidLayerCream(Cream cream) {
        this.midLayerCream = cream;
    }

    public void addTopping(Topping topping) {
        this.topping = topping;
    }

    public void addMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return this.cakeLayer + " with " + this.numCakeLayers + " layers" + (numCakeLayers > 1 ? " and " + this.midLayerCream : "") + " topped with " + this.topLayerCream + " and " + this.topping + ". Message says: " + this.message;
    }



}