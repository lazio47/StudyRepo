package aula08.Exercicio8_2;

public class Cereal extends AlimentoVegetariano{
    
    public Cereal(String nome, double proteinas, double calorias, double peso) {
        super(nome, proteinas, calorias, peso);
    }

    @Override
    public String toString() {
        return "Cereal " + getNome() + ", Proteínas "+getProteinas()+", Calorias "+getCalorias()+", Peso "+getPeso();
    }
    
}
