public class CakeMaster {

    public CakeMaster() {}

    private CakeBuilder cakeBuilder;
    
    public void setCakeBuilder(CakeBuilder cakeBuilder) {
        this.cakeBuilder = cakeBuilder;
    }
    
    public void createCake(String message) {
         createCake(Shape.Round, 1, message);
    }

    public void createCake(int layers, String message) {
        createCake(Shape.Round, layers, message);
    }

    public void createCake(Shape shape, int layers, String message) {
        cakeBuilder.createCake();

        cakeBuilder.setCakeShape(shape);
        cakeBuilder.addMessage(message);
        for (int i = 0; i < layers; i++) {
            cakeBuilder.addCakeLayer();
        }
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        
        

    }
    

    public Cake getCake() {
        return cakeBuilder.getCake();
    }
}
