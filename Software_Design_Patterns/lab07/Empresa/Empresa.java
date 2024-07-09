public class Empresa {

    private Registos registos;
    private Database database;
    // private Eadapter adapter;

    public Empresa(Database database, Registos registos) {
        this.registos = new Registos();
        this.database = new Database();
        }

    public void insere(Empregado empregado) {
        database.addEmployee(new Eadapter(empregado));
    }

    public void remove(int codigo) {
        database.deleteEmployee(codigo);
    }

    public boolean isEmpregado(long codigo) {
        for (Employee emp : database.getAllEmployees()) {
            if (emp.getEmp_num() == codigo) {
                return true;
            }
        }
        return false;
    }

    public void listaDeEmpregados() {
        database.getAllEmployees();
    }


    public Employee[] getAllEmployees() {

        
        Employee lista_empregados[] = new Employee[database.getAllEmployees().length + registos.listaDeEmpregados().size()];
        for (Empregado emp : registos.listaDeEmpregados()) {
            Employee empregado = new Eadapter(emp);
            for (int i = 0; i < lista_empregados.length; i++) {
                if (lista_empregados[i] == null) {
                    lista_empregados[i] = empregado;
                    break;
                }
            }
        }
        for (Employee emp : database.getAllEmployees()) {
            for (int i = 0; i < lista_empregados.length; i++) {
                if (lista_empregados[i] == null) {
                    lista_empregados[i] = emp;
                    break;
                }
            }
        }
        System.out.println("All employees:");
        for (Employee employee : lista_empregados) {
            System.out.println(employee.getName() + " - " + employee.getEmp_num() + " - " + employee.getSalary() + "€");
        }

        return lista_empregados;
    }

}
