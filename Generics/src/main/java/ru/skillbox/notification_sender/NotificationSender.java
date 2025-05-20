package ru.skillbox.notification_sender;
import ru.skillbox.notification.*;
import java.util.*;
/**
 * Сервис по отправке уведомлений
 *
 * @param <T> Вид отправляемого уведомления
 */
public interface NotificationSender<T extends Notification> {
    String messageTitle = "message: ";
    String receiverTitle = "receivers: ";
    /**
     * Отправить одно уведомление
     *
     * @param notification уведомление
     */
    void send(T notification);
    /**
     * Отправка множества уведомлений
     *
     * @param notifications список уведомлений
     */
    void send(List<T> notifications);
}