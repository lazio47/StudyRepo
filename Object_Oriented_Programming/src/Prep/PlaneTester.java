package Prep;

import java.util.Scanner;

public class PlaneTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlaneManager manager = new PlaneManager();
        Plane p1 = new CommercialPlane("PTAB", "Boeing", "B737", 2005, 500, 950, 25);
        Plane p2 = new CommercialPlane("PTAC", "Airbus", "A380", 2007, 587, 980, 30);
        Plane p3 = new MilitaryPlane("MTAC", "Lockheed-Martin", "Sr-71 Blackbird", 1998, 1, 2600, 8);
        Plane p4 = new MilitaryPlane("MTAB", "Lockheed", "F-117 Nighthawk", 1995, 1, 2400, 6);
        manager.addPlane(p1);
        manager.addPlane(p2);
        manager.addPlane(p3);
        manager.addPlane(p4);
        int option;
        String id;
        do{
            System.out.println("1. Adicionar");
            System.out.println("2. Remover ");
            System.out.println("3. Procurar ");
            System.out.println("4. Imprimir todos ");
            System.out.println("5. Imprimir comerciais ");
            System.out.println("6. Imprimir militares ");
            System.out.println("7. Imprimir o mais rápido ");
            System.out.println("0. sair");
            option = sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:

                break;
                case 2:
                id = sc.nextLine();
                manager.removePlane(id);
                break;
                case 3:
                id = sc.nextLine();
                System.out.println(manager.searchPlane(id));
                break;
                case 4:
                manager.printAllPlanes();
                case 5:
                System.out.println(manager.getCommercialPlanes());
                break;
                case 6:
                System.out.println(manager.getMilitaryPlanes());
                break;
                case 7:
                System.out.println(manager.getFastestPlane());
                break;
                case 0:
                System.out.println("A sair...");
                break;
                default:
                System.out.println("Opção inválida!");
            }
        }while(option!=0);
        sc.close();
    }
}
