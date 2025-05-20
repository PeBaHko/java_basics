public class Manager implements Employee {
  private final int salary;
  private final Company company;
  private int maxProfitWorker = 140000;
  private int minProfitWorker = 115000;
  private final float percent = 0.05f;
  private final int bonus = (int) Math.round(((Math.random()*(maxProfitWorker-minProfitWorker))+minProfitWorker)*percent);
  public Manager(Company company, int salary) {
    this.company = company;
    this.salary = salary + bonus;
  }
  public int getMonthSalary() {
    return salary;
  }
}