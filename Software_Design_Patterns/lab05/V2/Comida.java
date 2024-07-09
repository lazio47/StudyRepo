public abstract class Comida implements Portion {

    protected State state;
    protected Temperature temperature;

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Temperature getTemperature() {
        return temperature;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": Temperature" + temperature + ", State " + state;
    }
    
}
