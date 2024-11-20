package org.SHA.core.port.service;
import org.SHA.core.domain.Notification;
import java.util.List;

// Interface som definerer metoder for sending og henting av varsler.
public interface NotificationService {
    void sendNotification(Notification notification);
    List<Notification> getAllNotifications();
}
