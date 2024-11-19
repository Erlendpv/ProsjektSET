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

    // Metode for å slette en enhet
    public void deleteDevice(String userId, String deviceId) {
        // Finn brukeren fra userRepository
        User user = userRepository.findById(userId);
        if (user != null) {
            // Hent listen over enheter
            List<Device> devices = user.getDevices();
            // Bruk en tradisjonell for-løkke for å fjerne enheten
            for (int i = 0; i < devices.size(); i++) {
                Device device = devices.get(i);
                if (device.getDeviceId().equals(deviceId)) {
                    devices.remove(i); // Fjern enheten fra listen
                    i--; // Juster indeksen etter fjerning for å unngå å hoppe over elementer
                }
            }
            userRepository.save(user); // Oppdater brukeren etter fjerning

            // Slett enheten fra enhetslageret ved å bruke DeviceRepository
            deviceRepository.delete(deviceId);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

}
