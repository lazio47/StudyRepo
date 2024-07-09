public class ContainerFactory {
    public static Container create(Portion portion) {
        Container container = null;
        switch (portion.getState().toString() + portion.getTemperature()) {
            case "SolidCOLD":
                container = new PlasticBag();
                break;
            case "LiquidWARM":
                container = new TermicBottle();
                break;
            case "LiquidCOLD":
                container = new PlasticBottle();
                break;
            case "SolidWARM":
                container = new Tupperware();
                break;
        }

        container.setPortion(portion);
        return container;
    }
}
