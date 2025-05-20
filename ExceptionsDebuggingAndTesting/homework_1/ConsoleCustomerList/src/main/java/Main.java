import java.util.*;
import org.apache.logging.log4j.*;

public class Main {
  public static final Logger logger = LogManager.getLogger(Main.class);
  private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
  private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров\n\thelp\n\texit";
  private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
  private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    CustomerStorage executor = new CustomerStorage();
    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine();
      String[] tokens = command.split("\\s+", 2);
      if(menu(executor, tokens)) break;
    }
  }
  private static boolean menu(CustomerStorage executor, String[] tokens) {
    boolean exit = false;
    try {
      switch (tokens[0]) {
        case "add"    -> add(executor, tokens[1]);
        case "list"   -> executor.listCustomers();
        case "remove" -> executor.removeCustomer(tokens[1]);
        case "count"  -> System.out.println("There are " + executor.getCount() + " customers");
        case "help"   -> System.out.println(helpText);
        case "exit"   -> exit = true;
        default       -> System.out.println(COMMAND_ERROR);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("отсутствуют элементы в массиве");
      //LogManager.getLogger().error("отсутствуют аргументы в массиве", e);
      logger.error("отсутствуют аргументы в массиве", e);
    } catch (IncorrectQuantityOfComponentsException e) {
      System.out.println("некорректное количество аргументов");
     // LogManager.getLogger().debug("некорректное количество аргументов");
      LogManager.getLogger().error("некорректное количество аргументов", e);
    } catch (WrongPhoneException e) {
    //  LogManager.getLogger().debug("неправильный формат номера");
      LogManager.getLogger().error(e);
      System.out.println("неправильный формат номера");
    } catch (WrongEmailException e) {
      System.out.println("неправильный формат электронной почты");
     // LogManager.getLogger().debug("неправильный формат электронной почты");
      LogManager.getLogger().error("неправильный формат электронной почты", e);
      }
    return exit;
  }
  private static void add(CustomerStorage executor, String line) {
    executor.addCustomer(line);
  }
}