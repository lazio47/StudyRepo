public class TeamMemberDecorator extends EmployeeDecorator {

    public TeamMemberDecorator(EmployeeInterface employee) {
        super(employee);
    }

    public void work(){
        super.work();
        System.out.println(".. as a Team Member!!!");
    }
}
