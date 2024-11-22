import org.SHA.core.domain.Device;
import org.SHA.core.domain.User;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.stub.UserRepositoryStub;
import org.SHA.core.usecase.AddDeleteUserUseCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private final AddDeleteUserUseCase addDeleteUserUseCase = new AddDeleteUserUseCase(new UserRepositoryStub());

    @Test
    public void testAddUser() {
        UserDTO userDTO = addDeleteUserUseCase.addUser("Benji", "Benji@hotmail.com");
        assertNotNull(userDTO.getUserId());
        assertEquals("Benji", userDTO.getUsername());
        assertEquals("Benji@hotmail.com", userDTO.getEmail());
        assertTrue(userDTO.getDeviceIds().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUgyldigEmail() {
        addDeleteUserUseCase.addUser("Benji", "ugyldigemail");
    }

    @Test
    public void testAddDevice() {
        UserDTO userDTO = addDeleteUserUseCase.addUser("Benji", "Benji@hotmail.com");
        User user = new User(userDTO.getUserId(), userDTO.getUsername(), userDTO.getEmail());
        Device device = new Device("12345", "Høyttaler");

        user.addDevice(device);

        assertEquals(1, user.getDevices().size());
        assertTrue(user.getDevices().contains(device));
    }

    @Test
    public void testRemoveDevice() {
        UserDTO userDTO = addDeleteUserUseCase.addUser("Benji", "Benji@hotmail.com");
        User user = new User(userDTO.getUserId(), userDTO.getUsername(), userDTO.getEmail());
        Device device = new Device("12345", "Høyttaler");

        user.addDevice(device);
        user.removeDevice(device);

        assertTrue(user.getDevices().isEmpty());
    }
}
