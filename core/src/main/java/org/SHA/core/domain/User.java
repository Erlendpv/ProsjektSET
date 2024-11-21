package org.SHA.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// Informasjon om brukeren
public class User {
    private String userId;
    private String username;
    private String email;
    private List<Device> devices;

    // Validerer at userId og username ikke er tomme, og at e-postadressen er gyldig.
    // Dersom noe ikke er gyldig, kastes IllegalArgumentException.
    public User(String userId, String username, String email) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("userId ikke gyldig");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("username ikke gyldig");
        }
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("ikke gyldig e-postadresse");
        }

        // ArrayList for å holde brukerens enheter
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.devices = new ArrayList<>();
    }

    // Validerer om e-postadressen er gyldig ved hjelp av regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    // Gettere og settere for å kunne oppdatere informasjon om brukeren
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("ikke gyldig e-postadresse");
        }
        this.email = email;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    // Relevante metoder

    // Logikk for å legge til enhet
    public void addDevice(Device device) {
        if (!devices.contains(device)) {
            devices.add(device);
        }
    }

    // Logikk for å fjerne enhet
    public void removeDevice(Device device) {
        devices.remove(device);
    }
}
