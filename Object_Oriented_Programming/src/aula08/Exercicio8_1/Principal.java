package aula08.Exercicio8_1;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {

        int distAtual = 0;

        ArrayList<Veiculo> veiculos1 = new ArrayList<>();
        
        Veiculo motociclo1 = new Motociclo("ABC-123", "Yamaha", "Speed", 270, Tipo.estrada);

        Veiculo maiorDistancia=motociclo1; 

        Veiculo motociclo2 = new Motociclo("ABC-124", "BMW", "Speed", 270, Tipo.desportivo);

        Veiculo ligeiro1 = new AutomovelLigeiro("ABC-125", "Citroen", "2019", 300, 4, 9);

        Veiculo ligeiro2 = new AutomovelLigeiro("ABC-126", "Mazda", "BT-50", 300, 4, 15);

        Veiculo pp1 = new PesadoPassageiro("ABC-137", "Zhong Tong", "Climber",250 ,8 , 3400,63 );

        Veiculo pm1 = new PesadoMercadorias("ABC-157", "Scania", "Hi Roof",700 ,8 , 3400,3900 );

        Veiculo ppEletrico1 = new PesadoPassageiroEletrico("ABC-127", "Zhong Tong", "Climber",250 ,8 , 3400,63 );

        Veiculo taxi1 = new Taxi("ASD-123", "Renault", "Olko", 180, 4, 9, 1111);

        ppEletrico1.setMarca("Man");

        ((PesadoPassageiroEletrico) ppEletrico1).carregar(70);
        ((PesadoPassageiroEletrico) ppEletrico1).carregar(200);

        veiculos1.add(ppEletrico1);

        motociclo1.trajeto(450);
        motociclo1.trajeto(90);
        pm1.trajeto(900);
        
        veiculos1.add(motociclo2);
        veiculos1.add(motociclo1);
        veiculos1.add(ligeiro1);
        veiculos1.add(ligeiro2);
        veiculos1.add(taxi1);
        veiculos1.add(pp1);
        veiculos1.add(pm1);
        
        ligeiro2.trajeto(600);
        for(int i = 0; i < veiculos1.size(); i++){
            if (veiculos1.get(i).distanciaTotal()>distAtual){
                distAtual = veiculos1.get(i).distanciaTotal();
                maiorDistancia = veiculos1.get(i);

            }
        }

        Empresa empresa1 =new Empresa("Boolean Rents", 1111, "rents@booleans.com");
        for(Veiculo v: veiculos1){
            empresa1.addVeiculos(v);
        }

        System.out.println(empresa1.toString());
        System.out.println("Veículo com maior Distancia percorrida: "+maiorDistancia.toString()+", com "+maiorDistancia.distanciaTotal()+"km.");
        System.out.println("O veiculo "+ppEletrico1.getMarca()+" tem "+((PesadoPassageiroEletrico)ppEletrico1).autonomia()+"% de Autonomia.");


    }
}
