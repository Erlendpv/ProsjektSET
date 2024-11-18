import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;


import java.util.List;

public class NotificationRepositoryStubTest {

    @Test
    public void testSaveNotification() {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        Notification notification = new PremadeNotification("001", "Test Message", "user@example.com");

        repository.save(notification);

        List<Notification> notifications = repository.findAll();
        assertEquals(1, notifications.size());
        assertEquals("001", notifications.get(0).getNotificationId());
    }

    @Test
    public void testFindAllNotifications() {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();

        Notification notification1 = new PremadeNotification("001", "Message 1", "user1@example.com");
        Notification notification2 = new PremadeNotification("002", "Message 2", "user2@example.com");

        repository.save(notification1);
        repository.save(notification2);

        List<Notification> notifications = repository.findAll();
        assertEquals(2, notifications.size());
    }

    @Test
    public void testClearAllNotifications() {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();

        Notification notification = new PremadeNotification("001", "Test Message", "user@example.com");
        repository.save(notification);

        repository.clearAll();

        List<Notification> notifications = repository.findAll();
        assertTrue(notifications.isEmpty());
    }
}
