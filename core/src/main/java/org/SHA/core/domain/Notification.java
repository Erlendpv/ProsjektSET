package org.SHA.core.domain;

import java.time.LocalDateTime;

/**
 * Abstrakt baseklasse for varsler.
 */
public abstract class Notification {
    private String notificationId;
    private String message;
    private String recipient; // For push-varsler
    private LocalDateTime timestamp;

    public Notification(String notificationId, String message, String recipient) {
        this.notificationId = notificationId;
        this.message = message;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
    }

    public abstract void trigger();

    // Gettere og settere
    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
