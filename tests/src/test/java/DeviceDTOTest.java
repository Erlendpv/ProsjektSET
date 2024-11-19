import org.junit.Test;
import static org.junit.Assert.*;
import org.SHA.core.dto.DeviceDTO;


public class DeviceDTOTest {

    @Test
    public void testConstructorAndGetters() {

        DeviceDTO device = new DeviceDTO("12345", "Sensor", "Active");

        assertEquals("12345", device.getDeviceId());
        assertEquals("Sensor", device.getType());
        assertEquals("Active", device.getStatus());
    }

    @Test
    public void testSetDeviceId() {
        DeviceDTO device = new DeviceDTO("12345", "Sensor", "Active");

        device.setDeviceId("67890");
        assertEquals("67890", device.getDeviceId());
    }

    @Test
    public void testSetType() {
        DeviceDTO device = new DeviceDTO("12345", "Sensor", "Active");

        device.setType("Camera");
        assertEquals("Camera", device.getType());
    }

    @Test
    public void testSetStatus() {
        DeviceDTO device = new DeviceDTO("12345", "Sensor", "Active");

        device.setStatus("Inactive");
        assertEquals("Inactive", device.getStatus());
    }
}
