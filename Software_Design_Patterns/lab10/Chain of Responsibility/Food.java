public class Food {
    private String name;
    private int timeToCook;
    private String cookerType;
    public Food(String name, int timeToCook, String cookerType) {
        this.name = name;
        this.timeToCook = timeToCook;
        this.cookerType = cookerType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTimeToCook() {
        return timeToCook;
    }
    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }
    public String getCookerType() {
        return cookerType;
    }
    public void setCookerType(String cookerType) {
        this.cookerType = cookerType;
    }
}
