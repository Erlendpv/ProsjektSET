package org.SHA.core.dto;

// Dataoverføringsobjekt for varsler
public class NotificationDTO {
    private String notificationId;
    private String message;
    private String recipient;

    // Oppretter et NotificationDTO-objekt
    public NotificationDTO(String notificationId, String message, String recipient) {
        this.notificationId = notificationId;
        this.message = message;
        this.recipient = recipient;
    }

    public String getNotificationId() { return notificationId; }
    public String getMessage() { return message; }
    public String getRecipient() { return recipient; }
}