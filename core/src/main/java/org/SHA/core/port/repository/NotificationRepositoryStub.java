package org.SHA.core.port.repository;

import org.SHA.core.domain.Notification;
import java.util.ArrayList;
import java.util.List;

// Stub for NotificationRepository for testing
public class NotificationRepositoryStub implements NotificationRepository {
    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public void save(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Kan ikke lagre et null-varsel.");
        }
        notifications.add(notification);
    }

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(notifications); // Returnerer en kopi for å beskytte den interne listen
    }

    @Override
    public void clearAll() {
        notifications.clear(); // Tømmer alle varsler
    }
}
