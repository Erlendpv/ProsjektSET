package org.SHA.core.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String email;
    private List<Device> devices;

    public User(String userId, String username, String email) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("userId ikke gyldig");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("username ikke gyldig");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("ikke gyldig e-postadresse");
        }

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.devices = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Device> getDevices() {
        return devices; // Returnerer listen over brukerens enheter
    }

    public void addDevice(Device device) {
        if (!devices.contains(device)) {
            devices.add(device);
        }
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }
}
