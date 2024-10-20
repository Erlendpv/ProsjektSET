package org.SHA.core.dto;

public class DeviceDTO {
    private String deviceId;
    private String type;
    private String status;

    public DeviceDTO(String deviceId, String type, String status) {
        this.deviceId = deviceId;
        this.type = type;
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}