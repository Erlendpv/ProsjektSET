package org.SHA.core.domain;

import java.time.LocalDateTime;


public class CustomNotification extends Notification {
    private LocalDateTime scheduledTime;

    public CustomNotification(String notificationId, String message, String recipient, LocalDateTime scheduledTime) {
        super(notificationId, message, recipient);
        this.scheduledTime = scheduledTime;
    }

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

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
