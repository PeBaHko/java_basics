public class Customer {
  private final String name;
  private final String phone;
  private final String email;
  public String getName() { return name; }
  public String getPhone() { return phone; }
  public String getEmail() { return email; }
  public Customer (String name, String phone, String email) {
    String regexPhone = "[+][7][0-9]{10}";
    if(!phone.matches(regexPhone)) {
      throw new WrongPhoneException("Неверный формат номера", phone);
    }
    String regexEmail = "[A-Za-z0-9.-]+[@][A-Za-z0-9]+[.][A-Za-z0-9]+";
    if(!email.matches(regexEmail)) {
      throw new WrongEmailException("Неверный формат адреса электронной почты", email);
    }
    this.name = name;
    this.phone = phone;
    this.email = email;
  }
  public String toString() { return name + " - " + email + " - " + phone; }
}