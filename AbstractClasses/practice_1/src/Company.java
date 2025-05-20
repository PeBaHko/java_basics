import java.util.*;
public class Company {
  private final double profit;
  private final ArrayList<Employee> employees = new ArrayList<>();
  //private final double maxProfit = 15000000d;
  //private final double minProfit = 6000000d;
  public Company() {
      profit =  Math.round((Math.random()*((300-120)))+120)*50000;
      //
      //employees.size();
    /* TODO:
    */
  }
  public double getIncome() {
    // возвращает доход компании
    return profit;
  }
  public void hire(Employee employee) {
    employees.add(employee);
  }
  public void hireAll(Collection<Employee> employees) {
    this.employees.addAll(employees);
  }
  public void fire(Employee employee) {
    employees.remove(employee);
  }
  public ArrayList<Employee> getTopSalaryStaff(int count) {
    ArrayList<Employee> employees = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      employees.add(this.employees.get(i));
    }
    return employees;
  }
  public ArrayList<Employee> getLowestSalaryStaff(int count) {
    if (count == 0) {
      return new ArrayList<>();
    }
    ArrayList<Employee> employees = new ArrayList<>();
    for (int i = this.employees.size()-count; i < this.employees.size(); i++) {
      employees.add(this.employees.get(i));
    }
    return employees;
  }
  public ArrayList<Employee> getAllEmployees() {
    return employees;
  }
}