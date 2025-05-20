package ru.skillbox.notification_sender;
import ru.skillbox.notification.*;
import java.util.*;
public class PushNotificationSender implements NotificationSender<PushNotification> {
  @Override
  public void send(PushNotification notification) {
    String message = "PUSH" + System.lineSeparator() + "title: " +
                     notification.getTitle() +
                     System.lineSeparator() + receiverTitle +
                     notification.getAccount() +
                     System.lineSeparator() +
                     messageTitle + notification.formattedMessage() +
                     System.lineSeparator();
    System.out.println(message);
  }
  @Override
  public void send(List<PushNotification> notifications) {
    for (PushNotification notification : notifications) {
      send(notification);
    }
  }
}