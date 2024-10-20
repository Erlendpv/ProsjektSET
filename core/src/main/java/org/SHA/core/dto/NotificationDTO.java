package org.SHA.core.dto;

public class NotificationDTO {
    private String notificationId;
    private String message;
    private String type; // "premade" or "custom"
    private String category; // for premade notifications
    private String condition; // for custom notifications

    public NotificationDTO(String notificationId, String message, String type, String category, String condition) {
        this.notificationId = notificationId;
        this.message = message;
        this.type = type;
        this.category = category;
        this.condition = condition;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}