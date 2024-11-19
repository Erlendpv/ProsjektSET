import org.junit.Assert;
import org.junit.Test;
import org.SHA.core.domain.CustomNotification;
import org.SHA.core.usecase.CreateCustomNotificationUseCase;
import java.time.LocalDateTime;

public class CreateCustomNotificationTest {

    @Test
    public void testCreateCustomNotification() {
        CreateCustomNotificationUseCase creator = new CreateCustomNotificationUseCase();

        LocalDateTime scheduledTime = LocalDateTime.now().plusHours(1);
        CustomNotification notification = creator.create(
                "001", "Custom Message", "user@example.com", scheduledTime
        );

        Assert.assertNotNull(notification);
        Assert.assertEquals("001", notification.getNotificationId());
        Assert.assertEquals("Custom Message", notification.getMessage());
        Assert.assertEquals("user@example.com", notification.getRecipient());
        Assert.assertEquals(scheduledTime, notification.getScheduledTime());
    }
}
