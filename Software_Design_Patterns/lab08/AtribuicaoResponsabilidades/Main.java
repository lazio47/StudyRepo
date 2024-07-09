public class Main {
    public static void main(String[] args) {
        EmployeeInterface employee = new Employee();
        System.out.println("Empregado normal:");
        employee.start(new Date(2, 4, 2024));
        employee.work();
        employee.terminate(new Date(17, 4, 2024));

        System.out.println("\nEmpregado com responsabilidades adicionais:");
        employee = new TeamMemberDecorator(new TeamLeaderDecorator(new ManagerDecorator(employee)));
        employee.start(new Date(18, 4, 2024));
        employee.work();
        employee.terminate(new Date(22, 4, 2024));
    }
}
