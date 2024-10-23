package org.SHA.core.usecase;

import org.SHA.core.domain.CustomNotification;
import org.SHA.core.port.NotificationRepository;

//heihei
public class CreateCustomNotificationUseCase {
    private final NotificationRepository notificationRepository;

    public CreateCustomNotificationUseCase(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    public void execute(String notificationId, String message, String userId, String condition){
        CustomNotification notification = new CustomNotification(notificationId, message, userId, condition);
        notificationRepository.save(notification);
    }
}
