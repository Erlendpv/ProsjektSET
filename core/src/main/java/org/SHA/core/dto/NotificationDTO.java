package org.SHA.core.dto;

public class NotificationDTO {
    private String notificationId;
    private String message;
    private String recipient;

    // Konstruktør, gettere og settere
    public NotificationDTO(String notificationId, String message, String recipient) {
        this.notificationId = notificationId;
        this.message = message;
        this.recipient = recipient;
    }

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
