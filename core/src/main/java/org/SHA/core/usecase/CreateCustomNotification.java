package org.SHA.core.usecase;

import org.SHA.core.domain.CustomNotification;

import java.time.LocalDateTime;

public class CreateCustomNotification {

    public CustomNotification create(String id, String message, String recipient, LocalDateTime scheduledTime) {
        return new CustomNotification(id, message, recipient, scheduledTime);
    }
}
