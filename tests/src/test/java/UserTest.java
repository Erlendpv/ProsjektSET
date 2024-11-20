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


    /*Test for å se om illegalargumentexception blir brukt når Email er ugyldig
    @Test
    public void testUgyldigEmail() {
    //Arrange
        User user = new User("202", "Benji", "Benji@hotmail.com");
    }*/


    //Tester om enhet har blit lagt til og om det er den bestemte enheten.
    @Test
    public void testAddDevice() {

        //Arrange
        User user = new User("202", "Benji", "Benji@hotmail.com");
        Device device = new Device("Marshall", "Speaker");

        //Act
        user.addDevice(device);

        //Assert
        assertEquals(1, user.getDevices().size()); //test om listen inneholden 1 enhet
        assertTrue(user.getDevices().contains(device)); //test for å se om bestemt enhet ble lagt til
        assertFalse(user.getDevices().isEmpty()); //test for å se om listen er tom
    }

    //Tester om enhet har blit fjernet.
    @Test
    public void testRemoveDevice() {

        //Arrange
        User user = new User("202", "Benji", "Benji@hotmail.com");
        Device device = new Device("Marshall", "Speaker");
        user.addDevice(device); //legger til enhet til bruker

        //Act
        user.removeDevice(device); //fjerner enheten

        // Assert
        assertTrue(user.getDevices().isEmpty()); //test for å se om listen er tom

    }

}