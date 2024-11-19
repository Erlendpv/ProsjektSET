package org.SHA.core.domain;

// Forhåndsdefinert varsel
public class PremadeNotification extends Notification {

    // Oppretter et forhåndsdefinert varsel
    public PremadeNotification(String notificationId, String message, String recipient) {
        super(notificationId, message, recipient);
    }

    // Sender varselet umiddelbart
    @Override
    public void trigger() {
        System.out.println("Forhåndsdefinert varsel sendt til " + getRecipient() + ": " + getMessage());
    }
}
