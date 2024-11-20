package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.domain.Device;
import org.SHA.core.port.repository.UserRepository;
import org.SHA.core.port.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;

public class AddDeleteDeviceUseCase {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public AddDeleteDeviceUseCase(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }
    public void execute(String userId, String deviceId, String deviceType) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        User user = optionalUser.get();

        Device device = new Device(deviceId, deviceType);

        user.addDevice(device);

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
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println("User not found with id: " + userId);
            return false; // Returner false hvis brukeren ikke finnes
        }

        //Hente ut user-objekt fra optional. <-- kan nå behandles som user objekt
        User user = optionalUser.get();

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


