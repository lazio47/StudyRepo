public class SpongeCakeBuilder implements CakeBuilder {

    private Cake cake;

    public SpongeCakeBuilder() {
       
    }
    
    public void setCakeShape(Shape shape) {
        cake.setCakeShape(shape);
    }

    public void createCake() {
        cake = new Cake("Sponge Cake");
    }

    public void addCakeLayer() {
        cake.addCakeLayer();
    }

    public void addCreamLayer() {
        cake.addMidLayerCream(Cream.Red_Berries_Cream);
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
