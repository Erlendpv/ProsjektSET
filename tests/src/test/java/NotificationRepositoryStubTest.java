import org.SHA.core.port.stub.NotificationRepositoryStub;
import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import java.util.List;

// Testklasse for NotificationRepositoryStub.
public class NotificationRepositoryStubTest {

    @Test
    public void testSaveNotification() {
        // Oppretter et repository og et varsel
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        Notification notification = new PremadeNotification("001", "Test melding", "bruker@eksempel.no");

        // Lagrer varslet i repositoryet
        repository.save(notification);

        // Henter alle varsler og verifiserer at det ble lagret riktig
        List<Notification> notifications = repository.findAll();
        assertEquals(1, notifications.size());
        assertEquals("001", notifications.get(0).getNotificationId());
    }

    @Test
    public void testFindAllNotifications() {
        // Oppretter et repository og legger til flere varsler
        NotificationRepositoryStub repository = new NotificationRepositoryStub();

        Notification notification1 = new PremadeNotification("001", "Melding 1", "bruker1@eksempel.no");
        Notification notification2 = new PremadeNotification("002", "Melding 2", "bruker2@eksempel.no");

        repository.save(notification1);
        repository.save(notification2);

        // Henter alle varsler og verifiserer antall
        List<Notification> notifications = repository.findAll();
        assertEquals(2, notifications.size());
    }

    @Test
    public void testClearAllNotifications() {
        // Oppretter et repository og legger til et varsel
        NotificationRepositoryStub repository = new NotificationRepositoryStub();

        Notification notification = new PremadeNotification("001", "Test melding", "bruker@eksempel.no");
        repository.save(notification);

        // Tømmer repositoryet
        repository.clearAll();

        // Verifiserer at repositoryet er tomt
        List<Notification> notifications = repository.findAll();
        assertTrue(notifications.isEmpty());
    }
}
