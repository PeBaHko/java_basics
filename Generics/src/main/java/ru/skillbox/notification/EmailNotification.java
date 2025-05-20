package ru.skillbox.notification;
import java.util.*;
import lombok.*;
@Getter
@AllArgsConstructor
public class EmailNotification implements Notification {
  private final ArrayList<String> addresses;
  private final String subject;
  private final String notification;
  @Override
  public String formattedMessage() {
      return "<p>" + notification + "</p>";
  }
}