public class YogurtCakeBuilder implements CakeBuilder { 

    private Cake cake;

    public YogurtCakeBuilder() {

    }
    
    public void setCakeShape(Shape shape) {
        cake.setCakeShape(shape);
    }

    public void createCake() {
        cake = new Cake("Yogurt Cake");
    }

    public void addCakeLayer() {
        cake.addCakeLayer();
    }

    public void addCreamLayer() {
        cake.addMidLayerCream(Cream.Vanilla_Cream);
    }

    public void addTopLayer() {
        cake.addTopLayerCream(Cream.Red_Berries_Cream);
        }

    public void addTopping() {
        cake.addTopping(Topping.Chocolate);
    }

    public void addMessage(String m) {
        cake.addMessage(m);
    }

    public Cake getCake() {
        return cake;
    }
}
