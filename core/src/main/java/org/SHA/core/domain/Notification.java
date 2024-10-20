package org.SHA.core.domain;

public abstract class Notification {
    protected String notificationId;
    protected String message;

    public Notification(String notificationId, String message) {
        this.notificationId = notificationId;
        this.message = message;
    }

    public abstract void trigger();

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
}
