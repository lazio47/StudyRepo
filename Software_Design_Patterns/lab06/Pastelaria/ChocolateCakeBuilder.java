public class ChocolateCakeBuilder implements CakeBuilder {

    private Cake cake;

    public ChocolateCakeBuilder() {
     
    }

    public void setCakeShape(Shape shape) {
        cake.setCakeShape(shape);
    }

    public void createCake() {
        cake = new Cake("Soft Chocolate cake");
    }
    
    public void addCakeLayer() {
        cake.addCakeLayer();
    }

    public void addCreamLayer() {
        cake.addMidLayerCream(null);
    }

    public void addTopLayer() {
        cake.addTopLayerCream(Cream.Whipped_Cream);
    }

    public void addTopping() {
        cake.addTopping(Topping.Fruit);
    }

    public void addMessage(String m) {
        cake.addMessage(m);
    }

    public Cake getCake() {
        return cake;
    }
}
