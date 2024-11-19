package org.SHA.core.port;
import org.SHA.core.domain.Notification;
import java.util.ArrayList;
import java.util.List;

// Repository for å lagre og hente varsler
public class NotificationRepository {
    private final List<Notification> notifications = new ArrayList<>();

    // Lagrer et varsel i repositoryet
    public void save(Notification notification) {
        if (notification == null) {
            System.err.println("Kan ikke lagre et null-varsel.");
            return;
        }
        notifications.add(notification);
    }

    // Returnerer alle varsler
    public List<Notification> findAll() {
        return new ArrayList<>(notifications);
    }

    // Fjerner alle varsler
    public void clearAll() {
        notifications.clear();
    }
}
