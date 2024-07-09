public class TeamLeaderDecorator extends EmployeeDecorator {

    public TeamLeaderDecorator(EmployeeInterface employee) {
        super(employee);
    }
    
    public void work(){
        super.work();
        System.out.println(".. as a Team Leader!!!");
    }
}
