package org.SHA.core.port.stub;
import org.SHA.core.domain.Notification;
import org.SHA.core.port.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

// Denne klassen fungerer som en stub for NotificationRepository, og brukes primært for testing.
// Den lagrer varsler i en liste og tilbyr funksjonalitet for å legge til, hente og slette varsler.
public class NotificationRepositoryStub implements NotificationRepository {
    private final List<Notification> notifications = new ArrayList<>();


    // Lagrer et varsel i listen. Kaster en feil hvis varselet er null.
    @Override
    public void save(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Kan ikke lagre et null-varsel.");
        }
        notifications.add(notification);
    }

    // Returnerer en kopi av listen med varsler for å beskytte den interne strukturen.
    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(notifications);
    }

    // Tømmer alle varsler fra listen.
    @Override
    public void clearAll() {
        notifications.clear();
    }
}
