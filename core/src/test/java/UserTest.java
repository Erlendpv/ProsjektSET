import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.domain.User;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User("202", "Benji", "Benji@hotmail.com");
        assertEquals("202", user.getUserId());
        assertEquals("Benji", user.getUsername());
        assertEquals("Benji@hotmail.com", user.getEmail());
        assertTrue(user.getDevices().isEmpty());
    }
}