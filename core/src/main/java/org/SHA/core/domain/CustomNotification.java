package org.SHA.core.domain;

public class CustomNotification extends Notification {
    private String userId;
    private String condition;

    public CustomNotification(String notificationId, String message, String userId, String condition) {
        super(notificationId, message);
        this.userId = userId;
        this.condition = condition;
    }

    @Override
    public void trigger() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
