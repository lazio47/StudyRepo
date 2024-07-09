package aula04.Exercicio4_1;

public class Exercicio4_1 {
    public static void main(String[] args){
        Circulo c1 = new Circulo(4.0);
        Circulo c2 = new Circulo(5.0);

        Triangulo t1 = new Triangulo(3.0, 4.0, 5.0);
        Triangulo t2 = new Triangulo(3.0, 4.0, 5.0);

        Retangulo r1 = new Retangulo(2.0, 3.0);
        Retangulo r2 = new Retangulo(4.0, 9.0);



        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(r1.toString());
        System.out.println(r2.toString());

        if(c1.getRaio().equals(c2.getRaio())){
            System.out.println("As circunferências são iguais.");
        }

        if(r1.getcomprimento().equals(r2.getcomprimento())&&r1.getaltura().equals(r2.getaltura())){
            System.out.println("Os retângulos são iguais.");
        }

        if(t1.equals(t2)){
            System.out.println("Os Triângulos são iguais.");
        }
    }
    
}
