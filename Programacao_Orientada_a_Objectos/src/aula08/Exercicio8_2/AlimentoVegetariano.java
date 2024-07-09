package aula08.Exercicio8_2;

public class AlimentoVegetariano extends Alimento{
    private String nome;

    public AlimentoVegetariano(String nome, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNome() == null) ? 0 : getNome().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cereal other = (Cereal) obj;
        if (getNome() == null) {
            if (other.getNome() != null)
                return false;
        } else if (!getNome().equals(other.getNome()))
            return false;
        return true;
    }
}
