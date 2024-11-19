package org.SHA.core.port;
import org.SHA.core.domain.Notification;
import java.util.ArrayList;
import java.util.List;


public class NotificationRepositoryStub extends NotificationRepository {
    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public void save(Notification notification) {
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
