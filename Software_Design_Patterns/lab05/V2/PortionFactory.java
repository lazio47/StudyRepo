public class PortionFactory {

    public static Portion create(String food, Temperature temperature) {
      
        switch (food+temperature) {
            case "MeatCOLD":
                return new Tuna();
            case "MeatWARM":
                return new Pork();
            case "BeverageCOLD":
                return new FruitJuice("Lemon");
            case "BeverageWARM":
                return new Milk();
            default:
                return null;
        }

    }
}
    

