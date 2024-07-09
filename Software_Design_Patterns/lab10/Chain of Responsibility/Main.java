public class Main {
    public static void main(String[] args) {
        Food f1 = new Food("veggie burger", 19, "BurgerChef");
        Food f2 = new Food("Pasta Carbonara", 14, "PastaChef");
        Food f4 = new Food("sushi nigiri and sashimi", 14, "SushiChef");
        Food f3 = new Food("PLAIN pizza", 17, "PizzaChef");
        Food f5 = new Food("salad with tuna", 10, "SaladChef");
        Food f6 = new Food("strawberry ice cream and waffles dessert", 17, "DessertChef");

        Chef c1 = new SushiChef();
        Chef c2 = new PastaChef();
        Chef c3 = new BurgerChef();
        Chef c4 = new PizzaChef();
        Chef c5 = new DessertChef();
        c1.setNext(c2);
        c2.setNext(c3);
        c3.setNext(c4);
        c4.setNext(c5);

        System.out.println("Can I please get a veggie burger?");
        c1.cook(f1);
        System.out.println();

        System.out.println("Can I please get a Pasta Carbonara?");
        c1.cook(f2);
        System.out.println();

        System.out.println("Can I please get a PLAIN pizza, no toppings!?");
        c1.cook(f3);
        System.out.println();

        System.out.println("Can I please get a sushi nigiri and sashimi?");
        c1.cook(f4);
        System.out.println();

        System.out.println("Can I please get a salad with tuna?");
        c1.cook(f5);
        System.out.println();

        System.out.println("Can I please get a strawberry ice cream and waffles dessert?");
        c1.cook(f6);
        System.out.println();
    }
}
