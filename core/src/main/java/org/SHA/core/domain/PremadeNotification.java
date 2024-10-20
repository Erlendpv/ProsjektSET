package org.SHA.core.domain;

public class PremadeNotification extends Notification {
    private String category;

    public PremadeNotification(String notificationId, String message, String category) {
        super(notificationId, message);
        this.category = category;
    }

    @Override
    public void trigger() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}