package org.SHA.core.domain;
import java.time.LocalDateTime;

// Tilpasset varsel med planlagt sending
public class CustomNotification extends Notification {
    private LocalDateTime scheduledTime;

    // Oppretter et tilpasset varsel
    public CustomNotification(String notificationId, String message, String recipient, LocalDateTime scheduledTime) {
        super(notificationId, message, recipient);
        this.scheduledTime = scheduledTime;
    }

    // Sjekker og sender varselet
    @Override
    public void trigger() {
        if (LocalDateTime.now().isAfter(scheduledTime)) {
            System.out.println("Tilpasset varsel sendt til " + getRecipient() + ": " + getMessage());
        } else {
            System.out.println("Varsel planlagt for: " + scheduledTime);
        }
    }

    // Getter og setter
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
