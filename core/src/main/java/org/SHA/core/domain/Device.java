package org.SHA.core.domain;

public class Device {
    private String deviceId;
    private String type;
    private String status;

    public Device(String deviceId, String type) {
        this.deviceId = deviceId;
        this.type = type;
        this.status = "Frakoblet";
    }

    public String getDeviceId() {
        return deviceId; // Returnerer enhetens ID
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
