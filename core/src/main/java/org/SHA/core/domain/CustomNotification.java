package org.SHA.core.domain;
import java.time.LocalDateTime;

// Klasse for tilpassede varsler med planlagt sending
public class CustomNotification extends Notification {
    private LocalDateTime scheduledTime;

    // Konstruktør som oppretter et tilpasset varsel
    public CustomNotification(String notificationId, String message, String recipient, LocalDateTime scheduledTime) {
        super(notificationId, message, recipient);
        if (scheduledTime == null || scheduledTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Planlagt tid må være i fremtiden.");
        }
        this.scheduledTime = scheduledTime;
    }

    // Metode som sjekker og sender varselet
    @Override
    public void trigger() {
        if (LocalDateTime.now().isAfter(scheduledTime)) {
            System.out.println("Tilpasset varsel sendt til " + getRecipient() + ": " + getMessage());
        } else {
            System.out.println("Varsel planlagt for: " + scheduledTime);
        }
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }
}
