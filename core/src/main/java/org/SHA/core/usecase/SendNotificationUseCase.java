package org.SHA.core.usecase;

import org.SHA.core.domain.Notification;
import org.SHA.core.port.repository.NotificationRepository;

public class SendNotificationUseCase {
    private final NotificationRepository repository;

    public SendNotificationUseCase(NotificationRepository repository) {
        this.repository = repository;
    }

    public void execute(Notification notification) {
        if (notification == null) {
            System.err.println("Varsel er null. Kan ikke sende et null-varsel.");
            return;
        }
        try {
            notification.trigger(); // Trigger varselet
            repository.save(notification); // Lagrer i repository
        } catch (Exception e) {
            System.err.println("Feil under sending av varsel: " + e.getMessage());
        }
    }
}
