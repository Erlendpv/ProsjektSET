package org.SHA.core.domain;

import java.util.ArrayList;
import java.util.List;


//informasjon om brukeren
public class User {
    private String userId;
    private String username;
    private String email;
    private List<Device> devices;

    //Validerer at userID og username informasjonen om brukeren ikke er tomme, og validerer at e-post adressen er gyldig.
    //Dersom noe ikke er gyldig vil det brukes IllegalArgumentException.
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

        //Araylist for å holde brukerens enheter
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.devices = new ArrayList<>();
    }
    //For å kunne endre og oppdatere informasjon(data) om brukeren.
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
        this.email = email;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    //Her setter vi inn relevante metoder

    //logikk for å Legge til enhet
    public void addDevice(Device device) {
        if (!devices.contains(device)) {
            devices.add(device);
        }
    }

    //Logikk for å fjerne enhet
    public void removeDevice(Device device) {
        devices.remove(device);
    }

}
