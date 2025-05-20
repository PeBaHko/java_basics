package ru.skillbox.notification_sender;
import ru.skillbox.notification.*;
import java.util.*;
public class SmsNotificationSender implements NotificationSender<SmsNotification> {
  @Override
  public void send(SmsNotification notification) {
    String message = "SMS" +
                     System.lineSeparator() +
                     receiverTitle +
                     countNumbers(notification.getPhoneNumbers().size()) +
                     phoneNumbers(notification.getPhoneNumbers().iterator()) +
                     messageTitle +
                     notification.formattedMessage() +
                     System.lineSeparator();
    System.out.println(message);
  }
  private String countNumbers (int countNumbers) {
    return countNumbers > 1 ? System.lineSeparator() : "";
  }
  private String phoneNumbers(Iterator<String> iterator) {
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
  public void send(List<SmsNotification> notifications) {
    for (SmsNotification notification : notifications) {
      send(notification);
    }
  }
}