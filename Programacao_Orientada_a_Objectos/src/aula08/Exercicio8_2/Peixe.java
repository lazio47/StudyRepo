package aula08.Exercicio8_2;

public class Peixe extends Alimento{
    
    private String tipo;

    public Peixe(String tipo, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        Peixe other = (Peixe) obj;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Peixe " + tipo + ", Proteínas "+getProteinas()+", Calorias "+getCalorias()+", Peso "+getPeso();
    }
    

    
}
