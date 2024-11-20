package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.domain.Device;
import org.SHA.core.port.repository.UserRepository;
import org.SHA.core.port.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AddDeleteDeviceUseCase {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public AddDeleteDeviceUseCase(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }
    public void execute(String userId, String deviceType) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println("User not found with id: " + userId);
            return;
        }
        User user = optionalUser.get();

        // Generer en unik device ID
        String deviceId = generateUniqueDeviceId();

        // Opprett en ny enhet
        Device device = new Device(deviceId, deviceType);

        // Legg til enheten til brukeren
        user.addDevice(device);

        // Lagre oppdateringer
        userRepository.save(user);
        deviceRepository.save(device);
    }
    private String generateUniqueDeviceId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String deviceId;

        do {
            StringBuilder idBuilder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(characters.length());
                idBuilder.append(characters.charAt(index));
            }
            deviceId = idBuilder.toString();
        } while (deviceRepository.findById(deviceId) != null); // Sjekk mot eksisterende enheter

        return deviceId;
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


