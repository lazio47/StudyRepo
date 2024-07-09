public class ManagerDecorator extends EmployeeDecorator{

    public ManagerDecorator(EmployeeInterface employee) {
        super(employee);
    }

    public void work(){
        super.work();
        System.out.println(".. as a manager!!!");
    }
}
