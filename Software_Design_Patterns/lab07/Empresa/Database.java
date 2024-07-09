import java.util.Vector;

public class Database { // Data elements
    private Vector<Employee> employees; // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            return false;
        }
        employees.add(employee);
        return true;
    }

    public Employee deleteEmployee(long emp_num) {  
        for (Employee employee : employees) {
            if (employee.getEmp_num() == emp_num) {
                employees.remove(employee);
                return employee;
            }
        }
        System.out.println("Employee number " + emp_num + " not found");
        return null;
    }

    public Employee[] getAllEmployees() {
        Employee[] employeesArray = new Employee[employees.size()];
        employees.toArray(employeesArray);
        System.out.println("All employees:");
        for (Employee employee : employeesArray) {
            System.out.println(employee.getName() + " - " + employee.getEmp_num() + " - " + employee.getSalary() + "€");
        }
        return employeesArray;
    }
}
