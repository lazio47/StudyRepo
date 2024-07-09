package aula08.Exercicio8_2;

public class Legume extends AlimentoVegetariano{

    public Legume(String nome, double proteinas, double calorias, double peso) {
        super(nome, proteinas, calorias, peso);
    }
    
    @Override
    public String toString() {
        return "Legume " + getNome() + ", Proteinas "+getProteinas()+", Calorias "+getCalorias()+", Peso "+getPeso();
    }

    
}
