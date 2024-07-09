
public class VehicleFactory {
    public static Veiculo createMotociclo(String matricula, String marca, String modelo, double potencia, String tipo){
        return new Motociclo(matricula, marca, modelo, potencia, Tipo.valueOf(tipo));
    }

    public static Veiculo createAutomovelLigeiro(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int capacidadeBagageira){
        return new AutomovelLigeiro(matricula, marca, modelo, potencia, numeroQuadro, capacidadeBagageira);
    }

    public static Veiculo createTaxi(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int capacidadeBagageira, String numeroLicenca){
        return new Taxi(matricula, marca, modelo, potencia, numeroQuadro, capacidadeBagageira, numeroLicenca);
    }

    public static Veiculo createPPEletrico(String matricula, String marca, String modelo, int potencia, int peso, String numeroQuadro, int numMaxPassageiro, int bateria, int autonomia){
        return new PesadoPassageiroEletrico(matricula, marca, modelo, potencia, numeroQuadro, peso, numMaxPassageiro);
    }

    public static Veiculo createALEletrico(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int capacidadeBagageira, int bateria, int autonomia){
        return new AutomovelLigeiroEletrico(matricula, marca, modelo, potencia, numeroQuadro, capacidadeBagageira);
    }

    public static Veiculo createPesadoMercadorias(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int peso, double cargaMaxima){
        return new PesadoMercadorias(matricula, marca, modelo, potencia, numeroQuadro, peso, cargaMaxima);
    }

    public static Veiculo createPesadoPassageiros(String matricula, String marca, String modelo, int potencia, int peso, String numeroQuadro, int numMaxPassageiro){
        return new PesadoPassageiro(matricula, marca, modelo, potencia, numeroQuadro, peso, numMaxPassageiro);
    }
}
