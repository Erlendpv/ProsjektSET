package org.SHA.core.usecase;
import java.util.List;
import org.SHA.core.domain.User;
import org.SHA.core.domain.Device;
import org.SHA.core.port.UserRepository;
import org.SHA.core.port.DeviceRepository;

public class AddDeleteDeviceUseCase {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public AddDeleteDeviceUseCase(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    public void execute(String userId, String deviceId, String deviceType) {
        User user = userRepository.findById(userId);
        Device device = new Device(deviceId, deviceType);
        user.getDevices().add(device);
        userRepository.save(user);
        deviceRepository.save(device);
    }

    public boolean deleteDevice(String userId, String deviceId) {
        // Sjekk om enheten finnes i DeviceRepository
        Device device = deviceRepository.findById(deviceId);
        if (device == null) {
            System.out.println("Device not found with id: " + deviceId);
            return false; // Returner false hvis enheten ikke finnes
        }

        // Finn brukeren fra UserRepository
        User user = userRepository.findById(userId);
        if (user == null) {
            System.out.println("User not found with id: " + userId);
            return false; // Returner false hvis brukeren ikke finnes
        }

        // Fjern enheten fra brukerens enhetsliste
        List<Device> devices = user.getDevices();
        boolean deviceRemoved = false;
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getDeviceId().equals(deviceId)) {
                devices.remove(i);
                deviceRemoved = true;
                break;
            }
        }

        if (deviceRemoved) {
            // Oppdater bruker og fjern enheten fra DeviceRepository
            userRepository.save(user);
            deviceRepository.delete(deviceId);
            return true; // Suksess
        } else {
            System.out.println("Device not associated with the user.");
            return false; // Enheten var ikke knyttet til brukeren
        }
    }

}
