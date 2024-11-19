import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import org.SHA.core.port.NotificationRepositoryStub;
import org.SHA.core.port.NotificationService;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

// Testklasse for NotificationService.
public class NotificationServiceTest {

    @Test
    public void testSendNotification() {
        // Oppretter en stub for lagring av varsler
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        // Oppretter tjeneste for varsler
        NotificationService service = new NotificationService(repository);
        // Oppretter et varsel
        Notification notification = new PremadeNotification("001", "Testmelding", "bruker@eksempel.com");
        // Sender varslet
        service.sendNotification(notification);

        // Henter alle varsler fra repository
        List<Notification> notifications = repository.findAll();
        assertEquals(1, notifications.size()); // Sjekker at ett varsel er lagret
        assertEquals("Testmelding", notifications.get(0).getMessage()); // Sjekker innholdet i varslet
    }

    @Test
    public void testGetAllNotifications() {
        // Oppretter en stub for lagring av varsler
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        // Oppretter tjeneste for varsler
        NotificationService service = new NotificationService(repository);

        // Oppretter to varsler
        Notification notification1 = new PremadeNotification("001", "Melding 1", "bruker1@eksempel.com");
        Notification notification2 = new PremadeNotification("002", "Melding 2", "bruker2@eksempel.com");

        // Sender varslene
        service.sendNotification(notification1);
        service.sendNotification(notification2);

        // Henter alle varsler fra tjenesten
        List<Notification> notifications = service.getAllNotifications();
        assertEquals(2, notifications.size()); // Sjekker at to varsler er lagret
    }

    @Test
    public void testGetPremadeNotifications() {
        // Oppretter en stub for lagring av varsler
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        // Oppretter tjeneste for varsler
        NotificationService service = new NotificationService(repository);

        // Henter forhåndsdefinerte varsler
        List<Notification> premadeNotifications = service.getPremadeNotifications();
        assertEquals(3, premadeNotifications.size()); // Sjekker at tre forhåndsdefinerte varsler er tilgjengelige
        assertEquals("Husk å ta ut søpla!", premadeNotifications.get(0).getMessage()); // Sjekker innholdet i det første varslet
        assertEquals("Komfyren står fortsatt på!", premadeNotifications.get(1).getMessage()); // Sjekker innholdet i det andre varslet
        assertEquals("Vaskemaskinen er ferdig.", premadeNotifications.get(2).getMessage()); // Sjekker innholdet i det tredje varslet
    }
}


