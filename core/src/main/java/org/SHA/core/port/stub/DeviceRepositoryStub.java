package org.SHA.core.port.stub;

import org.SHA.core.domain.Device;
import org.SHA.core.port.repository.DeviceRepository;

import java.util.HashMap;
import java.util.Map;

public class DeviceRepositoryStub implements DeviceRepository {
    private final Map<String, Device> devices = new HashMap<>();

    @Override
    public Device findById(String deviceId) {
        return devices.get(deviceId); // Returner enhet fra listen
    }

    @Override
    public void save(Device device) {
        devices.put(device.getDeviceId(), device); // Lagrer enheten
    }

    @Override
    public void delete(String deviceId) {
        devices.remove(deviceId); // Sletter enheten
    }
}
