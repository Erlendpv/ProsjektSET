package org.SHA.core.usecase;

import org.SHA.core.port.service.DeviceControlService;
import java.util.HashMap;
import java.util.Map;

public class ControlDeviceUseCase implements DeviceControlService {
    // En enkel måte å lagre statusen til enheter på
    private final Map<String, String> deviceStatusMap = new HashMap<>();

    @Override
    public void sendCommand(String deviceId, String command) {
        // Endre statusen basert på kommandoen
        if ("turn on".equalsIgnoreCase(command)) {
            deviceStatusMap.put(deviceId, "ACTIVE");
            System.out.println("Command sent to device " + deviceId + ": turn on");
        } else if ("turn off".equalsIgnoreCase(command)) {
            deviceStatusMap.put(deviceId, "INACTIVE");
            System.out.println("Command sent to device " + deviceId + ": turn off");
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    @Override
    public String getDeviceStatus(String deviceId) {
        // Returner statusen til enheten, eller "UNKNOWN" hvis den ikke finnes
        return deviceStatusMap.getOrDefault(deviceId, "UNKNOWN");
    }
}
