package org.SHA.core.port;

import org.SHA.core.domain.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {
    private List<Notification> notifications = new ArrayList<>();

    public void save(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> findAll() {
        return new ArrayList<>(notifications);
    }

    public void clearAll() {
        notifications.clear();
    }
}
