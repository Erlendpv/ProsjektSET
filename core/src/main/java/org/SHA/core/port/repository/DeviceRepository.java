package org.SHA.core.port.repository;
import org.SHA.core.domain.Device;

// Interface for lagring og henting av enheter
public interface DeviceRepository {
    Device findById(String deviceId);
    void save(Device device);
    void delete(String deviceId);
}
