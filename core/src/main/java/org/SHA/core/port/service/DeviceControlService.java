package org.SHA.core.port.service;

public interface DeviceControlService {
    void sendCommand(String deviceId, String command);
    String getDeviceStatus(String deviceId);
}