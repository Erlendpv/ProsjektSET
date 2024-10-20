package org.SHA.core.usecase;

import org.SHA.core.port.DeviceControlService;

public class ControlDeviceUseCase {
    private final DeviceControlService deviceControlService;

    public ControlDeviceUseCase(DeviceControlService deviceControlService){
        this.deviceControlService = deviceControlService;
    }
    public void execute(String deviceId, String command){
        deviceControlService.sendCommand(deviceId, command);
    }
}

