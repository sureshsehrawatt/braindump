public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Encapsulation!");

        Employee employee = new Employee(1, "Demo Singh", 987654321);
        System.out.println(employee.toString());

        employee.setId(9);
        employee.setName(null);
        employee.setPhone(1234567890);
        System.out.println("Employee updated: " + employee.toString());
    }
}
