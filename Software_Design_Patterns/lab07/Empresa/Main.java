public class Main {

    public static void main(String[] args) {
        Registos r = new Registos();
        Empregado empregado1 = new Empregado("Joao", "arthur", 54 , 1531);
        r.insere(new Empregado("David", "AMORIM", 1, 10));
        r.insere(new Empregado("Ana", "Santos", 2, 1200));
        r.insere(new Empregado("Carlos", "Silva", 3, 900));
        r.insere(new Empregado("Maria", "Santos", 4, 1100));
        System.out.println("##################################\n");
        r.listaDeEmpregados().forEach(emp -> System.out.println(emp.nome() + " " + emp.apelido() + " - " + emp.salario() + "€"));
        System.out.println("##################################\n");
        r.remove(5);
        r.remove(3);
        System.out.println("##################################\n");
        r.listaDeEmpregados().forEach(emp -> System.out.println(emp.nome() + " " + emp.apelido() + " - " + emp.salario() + "€"));
        System.out.println("##################################\n");
        System.out.println(r.isEmpregado(5));
        
        System.out.println(r.isEmpregado(10));

        Database db = new Database();

        db.addEmployee(new Employee("David AMORIM", 1, 10));
        db.addEmployee(new Eadapter(empregado1));

        db.addEmployee(new Employee("Gabriel Santos", 5, 13445200));
        db.addEmployee(new Employee("Ana Santos", 200, 1200));
        db.addEmployee(new Employee("Carlos Silva", 10, 900));

        System.out.println("##################################\n");
        db.getAllEmployees();

        db.deleteEmployee(5);
        System.out.println("##################################\n");
        db.getAllEmployees();
        System.out.println("##################################\n");
        db.deleteEmployee(1);
        db.deleteEmployee(2);

        System.out.println("##################################\n");
        Empresa empresa = new Empresa(db, r);
        empresa.insere(new Empregado("David", "AMORIM", 1, 10));
        empresa.insere(new Empregado("Ana", "Santos", 2, 1200));
        empresa.remove(1);
        empresa.listaDeEmpregados();
    }
    
}
