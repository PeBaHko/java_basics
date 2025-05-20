public class IncorrectQuantityOfComponentsException extends RuntimeException {
  private final int currentNumber;
  private final int number;
  IncorrectQuantityOfComponentsException (String message, int currentNumber, int number) {
    super(message);
    this.currentNumber = currentNumber;
    this.number = number;
  }
  public int getCurrentNumber() { return currentNumber; }
  public int getNumber() { return number; }
}