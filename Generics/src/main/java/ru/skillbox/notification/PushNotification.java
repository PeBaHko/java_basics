package ru.skillbox.notification;
import lombok.*;
@Getter
@AllArgsConstructor
public class PushNotification implements Notification {
  private final String title;
  private final String account;
  private final String notification;
  @Override
  public String formattedMessage() {
      String emoji = "\ud83d\udc4b";
      return emoji + " " + notification;
  }
}