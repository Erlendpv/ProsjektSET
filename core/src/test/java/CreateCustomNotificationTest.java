import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.domain.CustomNotification;
import org.SHA.core.usecase.CreateCustomNotification;

import java.time.LocalDateTime;

public class CreateCustomNotificationTest {

    @Test
    public void testCreateCustomNotification() {
        CreateCustomNotification creator = new CreateCustomNotification();

        LocalDateTime scheduledTime = LocalDateTime.now().plusHours(1);
        CustomNotification notification = creator.create(
                "001", "Custom Message", "user@example.com", scheduledTime
        );

        assertNotNull(notification);
        assertEquals("001", notification.getNotificationId());
        assertEquals("Custom Message", notification.getMessage());
        assertEquals("user@example.com", notification.getRecipient());
        assertEquals(scheduledTime, notification.getScheduledTime());
    }
}
