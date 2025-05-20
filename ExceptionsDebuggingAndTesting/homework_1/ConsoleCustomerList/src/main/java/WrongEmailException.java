public class WrongEmailException extends RuntimeException {
  private final String currentEmail;
  public WrongEmailException(String message, String currentEmail) {
    super(message);
    this.currentEmail = currentEmail;
  }
  public String getCurrentEmail() { return currentEmail; }
}