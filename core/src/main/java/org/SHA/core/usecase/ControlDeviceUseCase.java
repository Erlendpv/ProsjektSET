package org.SHA.core.usecase;

import org.SHA.core.port.service.DeviceControlService;

import java.util.HashMap;
import java.util.Map;

public class ControlDeviceUseCase implements DeviceControlService {
    private final Map<String, String> deviceStatusMap = new HashMap<>();

    // Kontrollerer enhetens status
    public void controlDevice(String deviceId, boolean activate) {
        String status = activate ? "ACTIVE" : "INACTIVE";
        deviceStatusMap.put(deviceId, status);
        System.out.println("Enhet " + deviceId + " er nå " + status.toLowerCase() + ".");
    }

    @Override
    public String getDeviceStatus(String deviceId) {
        return deviceStatusMap.getOrDefault(deviceId, "UKJENT");
    }
}
