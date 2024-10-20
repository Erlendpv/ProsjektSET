package org.SHA.core.port;

import org.SHA.core.domain.Device;

public interface DeviceRepository {
    Device findById(String deviceId);
    void save(Device device);
    void delete(String deviceId);
}
