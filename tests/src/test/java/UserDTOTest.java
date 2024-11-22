import org.SHA.core.dto.UserDTO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDTOTest {

    @Test
    public void testUserDTO() {
        // Arrange (Opprettelse av opprinnelige verdier)
        List<String> opprinneligeDeviceIds = Arrays.asList("Enhet1", "Enhet2");
        UserDTO userDTO = new UserDTO("001", "Anonym", "Anonym@gmail.com", opprinneligeDeviceIds);

        // Assert (Test av opprinnelige verdier)
        assertEquals("Opprinnelig BrukerID", "001", userDTO.getUserId());
        assertEquals("Opprinnelig Brukernavn", "Anonym", userDTO.getUsername());
        assertEquals("Opprinnelig Email", "Anonym@gmail.com", userDTO.getEmail());
        assertEquals("Opprinnelige Enhets ID'er", opprinneligeDeviceIds, userDTO.getDeviceIds());

        // Act (Oppdatering av verdier)
        userDTO.setUserId("002");
        userDTO.setUsername("Benji");
        userDTO.setEmail("Benji@gmail.com");
        userDTO.setDeviceIds(Arrays.asList("Høytaler1", "Høytaler2"));

        // Assert (Test av oppdaterte verdier)
        assertEquals("Oppdatert BrukerID", "002", userDTO.getUserId());
        assertEquals("Oppdatert Brukernavn", "Benji", userDTO.getUsername());
        assertEquals("Oppdatert Email", "Benji@gmail.com", userDTO.getEmail());
        assertEquals("Oppdaterte Enhets ID'er", Arrays.asList("Høytaler1", "Høytaler2"), userDTO.getDeviceIds());
    }
}

