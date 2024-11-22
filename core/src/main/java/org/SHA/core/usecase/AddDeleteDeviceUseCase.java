package org.SHA.core.usecase;

import org.SHA.core.domain.Device;
import org.SHA.core.domain.User;
import org.SHA.core.dto.DeviceDTO;
import org.SHA.core.port.repository.DeviceRepository;
import org.SHA.core.port.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

public class AddDeleteDeviceUseCase {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    // Konstruktør som mottar implementasjoner av DeviceRepository og UserRepository
    public AddDeleteDeviceUseCase(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    // Oppretter og lagrer en ny enhet
    public DeviceDTO addDevice(String deviceType) {
        String deviceId = generateUniqueDeviceId();

        // Oppretter og lagrer enheten i repository
        Device newDevice = new Device(deviceId, deviceType);
        deviceRepository.save(newDevice);

        // Returnerer DeviceDTO med status "INACTIVE" som standard
        return new DeviceDTO(deviceId, deviceType, "INAKTIV");
    }

    // Sletter en enhet og fjerner den fra brukerens enhetsliste
    public boolean deleteDevice(String userId, String deviceId) {
        Optional<Device> deviceOptional = Optional.ofNullable(deviceRepository.findById(deviceId));
        if (deviceOptional.isEmpty()) {
            System.out.println("Enhet ikke funnet med ID: " + deviceId);
            return false;
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            System.out.println("Bruker ikke funnet med ID: " + userId);
            return false;
        }

        User user = userOptional.get();
        boolean removed = user.getDevices().removeIf(device -> device.getDeviceId().equals(deviceId));
        if (removed) {
            userRepository.save(user); // Oppdaterer brukeren
            deviceRepository.delete(deviceId); // Sletter enheten fra repository
            System.out.println("Enhet med ID " + deviceId + " ble slettet.");
            return true;
        } else {
            System.out.println("Enheten er ikke tilknyttet brukeren.");
            return false;
        }
    }

    // Genererer en unik enhets-ID
    private String generateUniqueDeviceId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String deviceId;

        do {
            StringBuilder idBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                int index = random.nextInt(characters.length());
                idBuilder.append(characters.charAt(index));
            }
            deviceId = idBuilder.toString();
        } while (deviceRepository.findById(deviceId) != null);

        return deviceId;
    }
}
