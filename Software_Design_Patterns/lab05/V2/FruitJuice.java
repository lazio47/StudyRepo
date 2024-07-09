public class FruitJuice extends Comida {

    private String fruit;

    public FruitJuice(String fruit) {
        super.setState(State.Liquid);
        super.setTemperature(Temperature.COLD);
        this.fruit = fruit;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + fruit + ", Temperature " + temperature + ", State " + state;
    }
}
