package org.SHA.core.usecase;

import org.SHA.core.domain.Device;
import org.SHA.core.dto.DeviceDTO;
import org.SHA.core.port.repository.DeviceRepository;

import java.util.Random;

public class AddDeleteDeviceUseCase {
    private DeviceRepository deviceRepository;

    // Konstruktør som initialiserer DeviceRepository
    public AddDeleteDeviceUseCase() {
        this.deviceRepository = deviceRepository;
    }

    // Legger til en ny enhet
    public DeviceDTO addDevice(String deviceType) {
        String deviceId = generateUniqueDeviceId(); // Generer unik ID
        Device newDevice = new Device(deviceId, deviceType); // Oppretter ny enhet med standardstatus
        deviceRepository.save(newDevice); // Lagrer enheten i repository

        System.out.println("Enhet opprettet med ID: " + deviceId);
        return new DeviceDTO(deviceId, deviceType, "INACTIVE"); // Returnerer DeviceDTO
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
        } while (deviceRepository.findById(deviceId) != null); // Sjekker for duplikater i repository

        return deviceId;
    }
}
