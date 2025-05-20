import java.util.*;
public class Main { //Math.round((Math.random()*((B-A)))+A)
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ArrayList<Company> companies = new ArrayList<>();
    String mask = "[0-9]";
    int command;
    String line;
    while(true) {
      System.out.println("создать новую компанию - \"new\"");
      System.out.println("посмотреть компанию - \"view\"");
      System.out.println("сокращение штата - \"reduction\"");
      System.out.println("выход - \"exit\"");
      System.out.print(">");
      line = scan.nextLine();
      if (line.equalsIgnoreCase("exit")) {
        break;
      }
      switch (line.toLowerCase()) {
        case "new" -> newCompany(companies);
        case "view" -> viewCompany(companies);
        case "reduction" -> staffReduction(companies);
        default -> System.out.println("Неизвестная команда");
      }
    }
  }
  public static void newCompany (ArrayList<Company> companies) {
    if (companies.size() == 1000) {
      System.out.println("Достигнуто максимальное число компаний");
    } else {
      Company company = new Company();
      ArrayList<Employee> operators = new ArrayList<>();
      // создание списка работников
      for (int i = 0; i < 180; i++) {
        operators.add(new Operator(company,(int) Math.round((Math.random()*25)+75)*1000));
      }
      company.hireAll(operators);
      ArrayList<Employee> managers = new ArrayList<>();
      for (int i = 0; i < 80; i++) {
        managers.add(new Manager(company, (int) Math.round((Math.random()*20)+80)*1000));
      }
      company.hireAll(managers);
      for (int i = 0; i< 10; i++) {
        company.hire(new TopManager(company, (int) Math.round((Math.random()*50)+150)*1000));
      }
      companies.add(company);
      System.out.println("Создана новая компания");
    }
  }
  public static void viewCompany(ArrayList<Company> companies) {
    if (companies.isEmpty()) {
      System.out.println("Список компаний пуст");
    } else {
      Scanner scan = new Scanner(System.in);
      String mask = "[0-9]+";
      int command;
      int countEmployees;
      int countTopStaff = 3;
      int countLowStaff = 7;
      ArrayList<Employee> topStaff = new ArrayList<>();
      ArrayList<Employee> lowStaff = new ArrayList<>();
      String line;
      while (true) {
        System.out.println("Общее количество компаний - " + companies.size());
        System.out.println("номер компании по списку");
        System.out.println("НАЗАД - \"back\"");
        System.out.print(">");
        line = scan.nextLine();
        if (line.equalsIgnoreCase("back")) {
          break;
        } else if (!line.matches(mask)) {
          System.out.println("Неизвестная команда");
        } else {
          command = Integer.parseInt(line);
          if ((command > companies.size())||(command <= 0)) {
            System.out.println("Компания не найдена");
          } else {
            command--;
            companies.get(command).getAllEmployees().sort(new EmployeeComparator());
            countEmployees = companies.get(command).getAllEmployees().size();
            System.out.println("Общее число сотрудников - " + countEmployees);
            System.out.println("Доход компании - " + String.format("%.0f", companies.get(command).getIncome()));
            if ((countTopStaff+countLowStaff) > countEmployees) {
              countTopStaff = countEmployees;
              countLowStaff = 0;
              System.out.println("все зарплаты");
            } else {
              System.out.println("самые высокие зарплаты");
            }
            topStaff = companies.get(command).getTopSalaryStaff(countTopStaff);
            lowStaff = companies.get(command).getLowestSalaryStaff(countLowStaff);
            for (Employee value : topStaff) {
              System.out.println(value.getMonthSalary());
            }
            if (!lowStaff.isEmpty()) {
              System.out.println("самые низкие зарплаты");
              for (Employee employee : lowStaff) {
                System.out.println(employee.getMonthSalary());
              }
            }
          }
        }
      }
    }
  }
  public static void staffReduction(ArrayList<Company> companies) {
    if (companies.isEmpty()) {
      System.out.println("Список компаний пуст");
    } else {
      Scanner scan = new Scanner(System.in);
      String mask = "[0-9]+";
      int command;
      boolean isFired;
      String line;
      Iterator<Employee> iterator;
      while (true) {
        isFired = false;
        System.out.println("Общее количество компаний - " + companies.size());
        System.out.println("номер компании по списку");
        System.out.println("НАЗАД - \"back\"");
        System.out.print(">");
        line = scan.nextLine();
        if (line.equalsIgnoreCase("back")) {
          break;
        } else if (!line.matches(mask)) {
          System.out.println("Неизвестная команда");
        } else {
          command = Integer.parseInt(line);
          if ((command > companies.size())||(command <= 0)) {
            System.out.println("Компания не найдена");
          } else {
            command--;
            companies.get(command).getAllEmployees().sort(new EmployeeComparator());
            if (companies.get(command).getAllEmployees().size() == 1) {
              companies.get(command).getAllEmployees().removeFirst();
            } else {
              iterator = companies.get(command).getAllEmployees().iterator();
              while (iterator.hasNext()) {
                if (isFired) {
                  iterator.next();
                  iterator.remove();
                  isFired = false;
                } else {
                  iterator.next();
                  isFired = true;
                }
              }
            }
            if (companies.get(command).getAllEmployees().isEmpty()) {
              companies.remove(command);
              System.out.println("Из компании уволен последний сотрудник и компания упразднена");
            } else {
              System.out.println("произведено сокращение штата");
              System.out.println("половина сотрудников уволена");
            }
          }
        }
      }
    }
  }
}