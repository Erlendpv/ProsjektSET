package org.SHA.core.port.service;

import org.SHA.core.domain.Notification;
import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification);
    List<Notification> getAllNotifications();
}
