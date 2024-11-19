package org.SHA.core.port;
import org.SHA.core.domain.Notification;
import java.util.List;

// Klasse for håndtering av varsler
public class NotificationService {
    private final NotificationRepository repository; // Repository for lagring av varsler

    // Konstruktør som initialiserer repository
    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    // Sender et varsel og lagrer det i repositoryet, og håndterer feil.
    public void sendNotification(Notification notification) {
        if (notification == null) {
            System.err.println("Varsel er null. Kan ikke sende et null-varsel.");
            return;
        }
        try {
            notification.trigger();
            repository.save(notification);
        } catch (Exception e) {
            System.err.println("Feil under sending av varsel: " + e.getMessage());
        }
    }

    // Henter alle lagrede varsler
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = repository.findAll();
        if (notifications == null) {
            System.err.println("Ingen varsler funnet.");
            return List.of();
        }
        return notifications;
    }
}
