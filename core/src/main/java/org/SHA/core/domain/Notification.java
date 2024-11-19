package org.SHA.core.domain;
import java.time.LocalDateTime;

// Klasse for varsler
public abstract class Notification {
    private String notificationId; // Unik ID
    private String message; // Varselmelding
    private String recipient; // Mottaker
    private LocalDateTime timestamp; // Opprettelsestid

    // Oppretter et varsel
    public Notification(String notificationId, String message, String recipient) {
        this.notificationId = notificationId;
        this.message = message;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
    }

    // Metode for å utløse varselet
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
