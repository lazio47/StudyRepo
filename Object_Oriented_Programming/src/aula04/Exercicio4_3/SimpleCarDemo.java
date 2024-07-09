package aula04.Exercicio4_3;

import java.util.Scanner;

class Car {
    public String make;
    public String model;
    public int year;
    public int kms;

    public Car(String make, String model, int year, int kms) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.kms = kms;
    }

    public void drive(int distance) {
        //TODO: acumular distância percorrida
    }
}

public class SimpleCarDemo {

    static Scanner sc = new Scanner(System.in);

    static void listCars(Car[] cars) {
        // TODO: lista todos os carros registados
        // Exemplo de resultado
        // Carros registados: 
        // Renault Megane Sport Tourer, 2015, kms: 35356
        // Toyota Camry, 2010, kms: 32456
        // Mercedes Vito, 2008, kms: 273891
        System.out.println("Carros registados:");
        for(int i=0; i<3;i++){
            System.out.println(cars[i].make+" "+cars[i].model+", "+cars[i].year+", kms: "+cars[i].kms);
        }

    }

    public static void main(String[] args) {

        Car[] cars = new Car[3];// TODO: completar
        cars[0] = new Car("Renault","Megane Sport Tourer",2015,35356);// TODO: completar
        cars[1] = new Car("Toyota","Camry",2010,32456);// TODO: completar
        cars[2] = new Car("Mercedes","Vito",2008,273891);// TODO: completar

        listCars(cars);

        // Adicionar 10 viagens geradas aleatoriamente
        for (int i=0; i<10; i++) {
            int j = (int)Math.round(Math.random()*2); // escolhe um dos 3 carros
            int kms = (int)Math.round(Math.random()*1000); // viagem até 1000 kms
            System.out.printf("Carro %d viajou %d quilómetros.\n", j, kms);
            
            // TODO: adicionar viagem ao carro j
            cars[j].kms += kms;
        }

        listCars(cars);

        sc.close();

    }
}