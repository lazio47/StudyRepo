public class Eadapter extends Employee {

    public Eadapter(Empregado emp) {
        super(emp.nome() + emp.apelido(), emp.codigo(), emp.salario());
    }
}
