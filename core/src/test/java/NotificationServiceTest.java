import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import org.SHA.core.port.NotificationService;

import java.util.List;

public class NotificationServiceTest {

    @Test
    public void testSendNotification() {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        NotificationService service = new NotificationService(repository);

        Notification notification = new PremadeNotification("001", "Test Message", "user@example.com");

        service.sendNotification(notification);

        List<Notification> notifications = repository.findAll();
        assertEquals(1, notifications.size());
        assertEquals("Test Message", notifications.get(0).getMessage());
    }

    @Test
    public void testGetAllNotifications() {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        NotificationService service = new NotificationService(repository);

        Notification notification1 = new PremadeNotification("001", "Message 1", "user1@example.com");
        Notification notification2 = new PremadeNotification("002", "Message 2", "user2@example.com");

        service.sendNotification(notification1);
        service.sendNotification(notification2);

        List<Notification> notifications = service.getAllNotifications();
        assertEquals(2, notifications.size());
    }
}
