package org.SHA.core.port;

public interface DeviceControlService {
    void sendCommand(String deviceId, String command);
    String getDeviceStatus(String deviceId);
}