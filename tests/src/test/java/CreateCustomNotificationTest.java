import org.junit.Assert;
import org.junit.Test;
import org.SHA.core.domain.CustomNotification;
import org.SHA.core.usecase.CreateCustomNotificationUseCase;
import java.time.LocalDateTime;

// Testklasse for opprettelse av tilpassede varsler.
public class CreateCustomNotificationTest {

    @Test
    public void testCreateCustomNotification() {
        // Oppretter en instans av CreateCustomNotificationUseCase
        CreateCustomNotificationUseCase creator = new CreateCustomNotificationUseCase();

        // Definerer tidspunkt for varslingen
        LocalDateTime scheduledTime = LocalDateTime.now().plusHours(1);

        // Oppretter et tilpasset varsel
        CustomNotification notification = creator.create(
                "001", "Tilpasset melding", "bruker@eksempel.no", scheduledTime
        );

        // Sjekker at varslet er korrekt opprettet
        Assert.assertNotNull(notification); // Sjekker at varslet ikke er null
        Assert.assertEquals("001", notification.getNotificationId()); // Sjekker ID
        Assert.assertEquals("Tilpasset melding", notification.getMessage()); // Sjekker meldingen
        Assert.assertEquals("bruker@eksempel.no", notification.getRecipient()); // Sjekker mottaker
        Assert.assertEquals(scheduledTime, notification.getScheduledTime()); // Sjekker tidspunktet
    }
}
