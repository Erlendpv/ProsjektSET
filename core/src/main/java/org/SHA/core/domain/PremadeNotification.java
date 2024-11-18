package org.SHA.core.domain;

public class PremadeNotification extends Notification {

    public PremadeNotification(String notificationId, String message, String recipient) {
        super(notificationId, message, recipient);
    }

    @Override
    public void trigger() {
        System.out.println("Forhåndsdefinert varsel sendt til " + getRecipient() + ": " + getMessage());
    }
}
