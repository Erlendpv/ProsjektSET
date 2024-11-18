package org.SHA.core.usecase;
import org.SHA.core.domain.CustomNotification;
import java.time.LocalDateTime;

// Use case klasse for å opprette tilpassede varsler
public class CreateCustomNotificationUseCase {

    // Oppretter og returnerer et CustomNotification-objekt og validerer inputdata før opprettelsen av et varsel.
    public CustomNotification create(String id, String message, String recipient, LocalDateTime scheduledTime) {
        if (id == null || message == null || recipient == null || scheduledTime == null) {
            throw new IllegalArgumentException("Alle parametere må være satt.");
        }
        return new CustomNotification(id, message, recipient, scheduledTime);
    }

}
