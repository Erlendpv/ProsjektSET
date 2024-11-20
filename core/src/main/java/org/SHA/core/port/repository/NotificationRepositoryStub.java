package org.SHA.core.port.repository;
import org.SHA.core.domain.Notification;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepositoryStub extends NotificationRepository {
    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public void save(Notification notification) {
        if (notification == null) {
            System.err.println("Kan ikke lagre et null-varsel.");
            return;
        }
        notifications.add(notification);
    }

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(notifications);
    }

    @Override
    public void clearAll() {
        notifications.clear();
    }
}
