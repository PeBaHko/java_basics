public class WrongPhoneException extends RuntimeException {
  private final String currentPhone;
  public WrongPhoneException(String message, String currentPhone) {
    super(message);
    this.currentPhone = currentPhone;
  }
  public String getCurrentPhone() { return currentPhone; }
}