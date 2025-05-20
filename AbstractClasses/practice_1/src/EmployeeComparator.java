import java.util.*;
public class EmployeeComparator implements Comparator<Employee> {
  @Override
  public int compare(Employee employee1, Employee employee2) {
    return -(employee1.getMonthSalary() - employee2.getMonthSalary());
  }
}