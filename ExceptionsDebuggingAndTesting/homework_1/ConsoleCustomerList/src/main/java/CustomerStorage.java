import org.apache.logging.log4j.*;

import java.util.*;

public class CustomerStorage {
  private final Map<String, Customer> storage;

  public CustomerStorage() { storage = new HashMap<>(); }

  public void addCustomer(String data) {
    final int INDEX_NAME = 0;
    final int INDEX_SURNAME = 1;
    final int INDEX_EMAIL = 2;
    final int INDEX_PHONE = 3;

    String[] components = data.split("\\s+");
    correctLength(components.length);
    String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
    storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    System.out.println("Новый клиент добавлен");
    //LogManager.getLogger().debug("Новый клиент добавлен");
    LogManager.getLogger().info("клиент с именем: [{}] добавлен", name);
  }
  private void correctLength(int length) throws IncorrectQuantityOfComponentsException {
    if(length != 4) throw new IncorrectQuantityOfComponentsException("Некорректное количество компонентов в строке", length, 4);
  }
  public void listCustomers() {
    if (storage.isEmpty()) {
      System.out.println("List is Empty");
    } else {
      storage.values().forEach(System.out::println);
    }
  }
  public void removeCustomer(String name) {
    if (getCustomer(name) == null) {
      System.out.println("Customer not found - is not exchange");
            //LogManager.getLogger.debug("клиент с именем: ["+ name +"] не найден");
        LogManager.getLogger().info("клиент с именем: [{}] не найден", name);
    } else {
      storage.remove(name);
            //LogManager.getLogger.debug("удаление выполнено");
        LogManager.getLogger().info("клиент [{}] удалён", name);
            System.out.println("удаление выполнено");
    }
  }
  public Customer getCustomer(String name) { return storage.get(name); }
  public int getCount() { return storage.size(); }
}