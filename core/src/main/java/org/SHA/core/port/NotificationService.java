package org.SHA.core.port;

import org.SHA.core.domain.Notification;
import org.SHA.core.port.NotificationRepository;

import java.util.List;

public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void sendNotification(Notification notification) {
        notification.trigger();
        repository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }
}
