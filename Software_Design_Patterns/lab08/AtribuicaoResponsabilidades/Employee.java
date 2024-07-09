public class Employee implements EmployeeInterface{

    @Override
    public void start(Date date) {
        System.out.println("Start date: " + date);
    }

    @Override
    public void terminate(Date date) {
        System.out.println("Terminate date: " + date);
    }

    @Override
    public void work() {
        System.out.println("Working...");
    }

}
