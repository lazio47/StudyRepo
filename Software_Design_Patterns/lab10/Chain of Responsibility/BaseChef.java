public class BaseChef implements Chef {
    private Chef next;

    @Override
    public void setNext(Chef chef) {
        this.next = chef;
    }

    @Override
    public void cook(Food food) {
        if(this.getClass().getSimpleName().equals(food.getCookerType())){
            System.out.println(this.getClass().getSimpleName()+
            ": Starting to cook "+food.getName()+". Out in "+
            food.getTimeToCook()+" minutes!");
        }else{
            System.out.println(this.getClass().getSimpleName()+
            ": I can't cook that.");
            if(next == null){
                System.out.println("We're sorry but that request can't be satisfied by our service!");
            }else{
                next.cook(food);
            }
        }
    }

}
