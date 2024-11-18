package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.domain.Device;
import org.SHA.core.port.UserRepository;
import org.SHA.core.port.DeviceRepository;

import java.util.Optional;

public class AddDeviceUseCase {
    private final UserRepository userRepository; //lagre brukere i databasen, grensesnitt.
    private final DeviceRepository deviceRepository; //lagre enheter i databasen, grensesnitt.

    //Avhengigshetsinjection
    public AddDeviceUseCase(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    public void execute(String userId, String deviceId, String deviceType) {
        //For å hente og skjekke om brukeren finnes, gjennom userId
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {throw new IllegalArgumentException("Bruker ikke funnet, med ID: " + userId);
        }
        User user = userOptional.get();

        //opprett en ny enhet
        Device device = new Device(deviceId, deviceType);

        //Legger til enheten i brukerens enhetsliste
        user.addDevice(device); // Bruker logikken fra User-klassen

        //oppdater brukeren og lagre enheten i repository
        userRepository.save(user);deviceRepository.save(device);
    }
}


