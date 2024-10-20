package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.domain.Device;
import org.SHA.core.port.UserRepository;
import org.SHA.core.port.DeviceRepository;

public class AddDeviceUseCase {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public AddDeviceUseCase(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    public void execute(String userId, String deviceId, String deviceType) {
        User user = userRepository.findById(userId);
        Device device = new Device(deviceId, deviceType);
        user.getDevices().add(device);
        userRepository.save(user);
        deviceRepository.save(device);
    }
}
