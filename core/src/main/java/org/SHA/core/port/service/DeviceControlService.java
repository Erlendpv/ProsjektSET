package org.SHA.core.port.service;

// Interface for kontroll av enheter
public interface DeviceControlService {
    // Kontrollér statusen til en enhet basert på brukerens valg (true for aktiv, false for inaktiv)
    void controlDevice(String deviceId, boolean activate);

    // Hent statusen til en enhet
    String getDeviceStatus(String deviceId);
}
