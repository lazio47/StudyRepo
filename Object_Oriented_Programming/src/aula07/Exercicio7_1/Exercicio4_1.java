package aula07.Exercicio7_1;

public class Exercicio4_1 {
    public static void main(String[] args){
        Circulo c1 = new Circulo(4.0, "Branco");
        Circulo c2 = new Circulo(5.0, "Amarelo");

        Triangulo t1 = new Triangulo(3.0, 4.0, 5.0, "Verde");
        Triangulo t2 = new Triangulo(3.0, 4.0, 5.0, "Verde");

        Retangulo r1 = new Retangulo(2.0, 3.0, "Castanho");
        Retangulo r2 = new Retangulo(4.0, 9.0, "Vermelho");



        System.out.println(c1);
        System.out.println(c2);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(r1);
        System.out.println(r2);

        if(c1.equals(c2)){
            System.out.println("As circunferências são iguais.");
        }

        if(r1.equals(r2)){
            System.out.println("Os retângulos são iguais.");
        }

        if(t1.equals(t2)){
            System.out.println("Os Triângulos são iguais.");
        }
    }
    
}
