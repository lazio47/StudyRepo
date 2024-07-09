public abstract class Container {
    protected Portion portion;

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "with Portion = " + portion.toString();
    }
    
}
