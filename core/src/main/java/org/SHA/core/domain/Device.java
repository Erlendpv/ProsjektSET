package org.SHA.core.domain;

public class Device {
    private String deviceId;
    private String type;
    private String status;

    public Device(String deviceId, String type) {
        this.deviceId = deviceId;
        this.type = type;
        this.status = "offline";
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

    //Relevante metoder her
}
