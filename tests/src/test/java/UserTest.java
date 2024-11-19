import org.SHA.core.domain.Device;
import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.domain.User;

public class UserTest {


    //Tester at enhet-listen er tom og at konstruktøren legger til UserID, Username og Email til brukeren riktig.
    @Test
    public void testUserConstructor() {

        //Arrange og Act
        User user = new User("202", "Benji", "Benji@hotmail.com");

        //Assert
        assertEquals("202", user.getUserId());
        assertEquals("Benji", user.getUsername());
        assertEquals("Benji@hotmail.com", user.getEmail());
        assertTrue(user.getDevices().isEmpty());
    }

    //Tester om 1 enhet har blit lagt til og om det er den bestemte enheten.
    @Test
    public void testAddDevice() {

        //Arrange
        User user = new User("202", "Benji", "Benji@hotmail.com");
        Device device = new Device("Marshall", "Speaker");

        //Act
        user.addDevice(device);

        //Assert
        assertEquals(1, user.getDevices().size());
        assertTrue("Device-list inneholder lagt til enhet", user.getDevices().contains(device));
    }
}