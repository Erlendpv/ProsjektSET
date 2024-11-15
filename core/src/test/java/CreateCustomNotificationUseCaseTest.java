import static org.mockito.Mockito.*;
import org.SHA.core.domain.CustomNotification;
import org.SHA.core.port.NotificationRepository;
import org.SHA.core.usecase.CreateCustomNotificationUseCase;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;

public class CreateCustomNotificationUseCaseTest {
    @Test
    public void testExecute() {

        NotificationRepository mockRepository = mock(NotificationRepository.class);


        CreateCustomNotificationUseCase useCase = new CreateCustomNotificationUseCase(mockRepository);

        String testNotificationId = "3303";
        String testMessage = "test message";
        String testUserId = "user881";
        String testCondition = "active";


        useCase.execute(testNotificationId, testMessage, testUserId, testCondition);


        ArgumentCaptor<CustomNotification> captor = ArgumentCaptor.forClass(CustomNotification.class);
        verify(mockRepository).save(captor.capture());
        CustomNotification actualNotification = captor.getValue();

        CustomNotification expectedNotification = new CustomNotification(testNotificationId, testMessage, testUserId, testCondition);
        //ASSERTS

        assertEquals(expectedNotification.getNotificationId(), actualNotification.getNotificationId());
        assertEquals(expectedNotification.getMessage(), actualNotification.getMessage());
        assertEquals(expectedNotification.getUserId(), actualNotification.getUserId());
        assertEquals(expectedNotification.getCondition(), actualNotification.getCondition());
    }
}
