package ru.skillbox.notification_sender;
import ru.skillbox.notification.*;
import java.util.*;
public class EmailNotificationSender implements NotificationSender<EmailNotification> {
  @Override
  public void send(EmailNotification notification) {
    String message = "EMAIL" +
                     System.lineSeparator() +
                     "subject: " +
                     notification.getSubject() +
                     System.lineSeparator() +
                     receiverTitle +
                     countNumbers(notification.getAddresses().size()) +
                     emailNumbers(notification.getAddresses().iterator()) +
                     messageTitle +
                     notification.formattedMessage() +
                     System.lineSeparator();
    System.out.println(message);
    }
  private String countNumbers (int countNumbers) {
    return countNumbers > 1 ? System.lineSeparator() : "";
  }
  private String emailNumbers(Iterator<String> iterator) {
    String stringNumbers = "";
    while (true) {
      stringNumbers = stringNumbers.concat(iterator.next());
      if (iterator.hasNext()) {
        stringNumbers = stringNumbers.concat(",").concat(System.lineSeparator());
      } else {
        stringNumbers = stringNumbers.concat(System.lineSeparator());
        return stringNumbers;
      }
    }
  }
    @Override
    public void send(List<EmailNotification> notifications) {
      for (EmailNotification notification : notifications) {
          send(notification);
      }
    }
}