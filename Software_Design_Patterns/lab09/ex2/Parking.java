public class Parking {
    Company3 company;

    Parking(Company3 company) {
        this.company = company;
    }

    public void allow(Person3 person) {

        Employee3 p = null;

        for (Employee3 employee : company.employees()) {
            if (employee.getPerson().equals(person)) {
                p = employee;
            }
        }

        if (p != null) {
            double avgSalary = company.avgSalary();
            if (p.getSalary() > avgSalary) {
                System.out.println("Access granted to " + person.getName() + " for parking.");
            } else {
                System.out.println("Access denied for " + person.getName() + " for");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}
