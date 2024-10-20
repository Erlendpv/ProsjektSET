package org.SHA.core.port;

import org.SHA.core.domain.Notification;

public interface NotificationRepository {
    Notification findById(String notificationId);
    void save(Notification notification);
    void delete(String notificationId);
}
