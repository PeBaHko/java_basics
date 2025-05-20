package ru.skillbox;
import ru.skillbox.notification.*;
import ru.skillbox.notification_sender.*;
import java.util.*;
public class Main {
  public static void main(String[] args) {
    String defaultMessage = "Спасибо за регистрацию в сервисе!";
    ArrayList<String> emailAddresses =  new ArrayList<>(List.of("oleg@java.skillbox.ru" , "masha@java.skillbox.ru", "yan@java.skillbox.ru"));
    String emailSubject = "Успешная регистрация!";
    ArrayList<String> phoneNumbers = new ArrayList<>(List.of("+70001234567"));
    String pushTitle = "Успешная регистрация!";
    String pushAccount = "o.yanovich";
    new EmailNotificationSender().send(List.of(new EmailNotification(emailAddresses, emailSubject, defaultMessage), new EmailNotification(emailAddresses, emailSubject, defaultMessage)));
    new SmsNotificationSender().send(List.of(new SmsNotification(phoneNumbers, defaultMessage), new SmsNotification(phoneNumbers, defaultMessage)));
    new PushNotificationSender().send(List.of(new PushNotification(pushTitle, pushAccount, defaultMessage), new PushNotification(pushTitle, pushAccount, defaultMessage)));
  }
}