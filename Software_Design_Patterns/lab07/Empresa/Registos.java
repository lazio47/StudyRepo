import java.util.ArrayList;
import java.util.List;

public class Registos {
    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees
    
    public Registos() {
        empregados = new ArrayList<>();
    }
    
    public void insere(Empregado emp) {
        System.out.println("Inserir empregado " + emp.nome() + " " + emp.apelido());
        empregados.add(emp);
    }

    public void remove(int codigo) {
        System.out.println("Remover empregado com código " + codigo);
        empregados.removeIf(emp -> emp.codigo() == codigo);
    }

    public boolean isEmpregado(int codigo) {
        if (empregados.stream().anyMatch(emp -> emp.codigo() == codigo)) {
            System.out.println("Existe empregado com código " + codigo);
            return true;
        }
        System.out.println("Não existe empregado com código " + codigo);
        return false;
    }

    public List<Empregado> listaDeEmpregados() {
        System.out.println("Lista de empregados:");
        return empregados;
    }
}
