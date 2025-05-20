public class Operator implements Employee {
  private final int salary;
  private final Company company;
  public Operator(Company company, int salary) {
    this.company = company;
    this.salary = salary;
  }
  public int getMonthSalary() {
    return salary;
  };
}