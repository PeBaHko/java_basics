package ru.skillbox.notification;
import lombok.*;
import java.util.*;
@Getter
@AllArgsConstructor
public class SmsNotification implements Notification {
  private final ArrayList<String> phoneNumbers;
  private final String notification;
  @Override
  public String formattedMessage() {
    return notification;
  }
}