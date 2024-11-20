package org.SHA.core.port.service;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import org.SHA.core.port.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

// Klasse for håndtering av varsler
public class NotificationService {
    private final NotificationRepository repository; // Repository for lagring av varsler

    // Konstruktør som initialiserer repository
    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    // Sender et varsel og lagrer det i repositoryet
    public void sendNotification(Notification notification) {
        if (notification == null) {
            System.err.println("Varsel er null. Kan ikke sende et null-varsel.");
            return;
        }
        try {
            notification.trigger(); // Utfører varselet
            repository.save(notification); // Lagrer varselet i repositoryet
        } catch (Exception e) {
            System.err.println("Feil under sending av varsel: " + e.getMessage());
        }
    }

    // Henter alle lagrede varsler
    public List<Notification> getAllNotifications() {
        return repository.findAll(); // Returnerer alle lagrede varsler
    }

    // Henter forhåndsdefinerte varsler
    public List<Notification> getPremadeNotifications() {
        List<Notification> premadeNotifications = new ArrayList<>();
        premadeNotifications.add(new PremadeNotification("001", "Husk å ta ut søpla!", "bruker@eksempel.no"));
        premadeNotifications.add(new PremadeNotification("002", "Komfyren står fortsatt på!", "bruker@eksempel.no"));
        premadeNotifications.add(new PremadeNotification("003", "Vaskemaskinen er ferdig.", "bruker@eksempel.no"));
        return premadeNotifications;
    }
}
