public class TopManager implements Employee {
  private final int salary;
  private final Company company;
  private final int MIN_PROFIT = 10000000;
  private final float bonus = 1.5f;
  public TopManager(Company company, int salary) {
    this.company = company;
    if (company.getIncome() > MIN_PROFIT) {
        this.salary = salary + (int) (salary * bonus);
    } else {
    this.salary = salary;}
  }
  public int getMonthSalary() {
    return salary;
  }
}