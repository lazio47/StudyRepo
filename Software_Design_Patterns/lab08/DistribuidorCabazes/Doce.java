public class Doce implements Produto {

    private String name;
    private double peso;

    public Doce(String name, int peso){
        this.name = name;
        this.peso = peso;
    }

    public double getWeight(){
        return peso;
    }

    @Override
    public void draw() {
        for (int i = 0; i < Caixa.ident; i++){
            System.out.print("\t");
        }
        System.out.println("Doce '" + name + "' - Weight : " + peso);
    }

}
