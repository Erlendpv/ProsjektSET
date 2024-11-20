package org.SHA.core.usecase;
import org.SHA.core.domain.Notification;
import org.SHA.core.port.repository.NotificationRepository;

// Use case klasse for å utføre sending av varsler.
public class SendNotificationUseCase {
    private final NotificationRepository repository;

    // Konstruktør som tar inn NotificationRepository for å lagre varsler.
    public SendNotificationUseCase(NotificationRepository repository) {
        this.repository = repository;
    }

    // Utfører sending av et varsel.
    public void execute(Notification notification) {
        if (notification == null) {
            System.err.println("Varsel er null. Kan ikke sende et null-varsel.");
            return;
        }
        try {
            notification.trigger(); // Utløser varselet
            repository.save(notification); // Lagrer varselet i repository
        } catch (Exception e) {
            System.err.println("Feil under sending av varsel: " + e.getMessage());
        }
    }
}
