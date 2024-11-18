package org.SHA.core.usecase;
import org.SHA.core.domain.CustomNotification;
import java.time.LocalDateTime;

// Use case for å opprette tilpassede varsler
public class CreateCustomNotificationUseCase {

    // Oppretter og returnerer et CustomNotification-objekt
    public CustomNotification create(String id, String message, String recipient, LocalDateTime scheduledTime) {
        return new CustomNotification(id, message, recipient, scheduledTime);
    }
}
