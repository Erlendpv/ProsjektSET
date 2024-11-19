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
}
