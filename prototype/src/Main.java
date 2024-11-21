package org.SHA.core.prototype;
import org.SHA.core.domain.CustomNotification;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import org.SHA.core.dto.DeviceDTO;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.repository.NotificationRepositoryStub;
import org.SHA.core.usecase.AddDeleteDeviceUseCase;
import org.SHA.core.usecase.AddDeleteUserUseCase;
import org.SHA.core.usecase.ControlDeviceUseCase;
import org.SHA.core.usecase.SendNotificationUseCase;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static final Map<String, UserDTO> users = new HashMap<>();
    private static final Map<String, DeviceDTO> devices = new HashMap<>();
    private static final Map<String, List<DeviceDTO>> userDevices = new HashMap<>();
    private static final Map<String, List<UserDTO>> deviceUsers = new HashMap<>();

    public static void main(String[] args) {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        AddDeleteUserUseCase userUseCase = new AddDeleteUserUseCase();
        AddDeleteDeviceUseCase deviceUseCase = new AddDeleteDeviceUseCase();
        ControlDeviceUseCase controlDeviceUseCase = new ControlDeviceUseCase();
        SendNotificationUseCase notificationUseCase = new SendNotificationUseCase(repository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SmartHome Assistant Prototype ---");
            System.out.println("1. Legg til bruker");
            System.out.println("2. Opprett enhet");
            System.out.println("3. Tildel enhet til bruker");
            System.out.println("4. Vis brukere og deres enheter");
            System.out.println("5. Vis enheter og deres brukere");
            System.out.println("6. Kontrollér enhet (skru på/av)");
            System.out.println("7. Send forhåndsdefinert varsel");
            System.out.println("8. Opprett og send tilpasset varsel");
            System.out.println("9. Vis alle varsler");
            System.out.println("10. Tøm alle varsler");
            System.out.println("11. Avslutt");
            System.out.print("Velg et alternativ: ");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> leggTilBruker(userUseCase, scanner);
                case 2 -> opprettEnhet(deviceUseCase, scanner);
                case 3 -> tildelEnhetTilBruker(scanner);
                case 4 -> visBrukereOgEnheter();
                case 5 -> visEnheterOgBrukere();
                case 6 -> kontrollerEnhet(controlDeviceUseCase, scanner);
                case 7 -> sendForhandsdefinertVarsel(notificationUseCase, scanner);
                case 8 -> opprettOgSendTilpassetVarsel(notificationUseCase, scanner);
                case 9 -> visAlleVarsler(repository);
                case 10 -> tomAlleVarsler(repository);
                case 11 -> avslutt();
                default -> System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }

    private static void leggTilBruker(AddDeleteUserUseCase userUseCase, Scanner scanner) {
        System.out.print("Skriv inn brukernavn: ");
        String navn = scanner.nextLine();
        System.out.print("Skriv inn e-post: ");
        String epost = scanner.nextLine();

        UserDTO user = userUseCase.addUser(navn, epost);
        users.put(user.getUserId(), user);
        userDevices.put(user.getUserId(), new ArrayList<>());
        System.out.println("Bruker lagt til: " + navn + " (ID: " + user.getUserId() + ")");
    }

    private static void opprettEnhet(AddDeleteDeviceUseCase deviceUseCase, Scanner scanner) {
        System.out.print("Skriv inn enhetstype: ");
        String type = scanner.nextLine();

        DeviceDTO device = deviceUseCase.addDevice(type);
        devices.put(device.getDeviceId(), device);
        deviceUsers.put(device.getDeviceId(), new ArrayList<>());
        System.out.println("Enhet opprettet: " + type + " (ID: " + device.getDeviceId() + ")");
    }

    private static void tildelEnhetTilBruker(Scanner scanner) {
        System.out.print("Skriv inn bruker-ID: ");
        String userId = scanner.nextLine();
        System.out.print("Skriv inn enhets-ID: ");
        String deviceId = scanner.nextLine();

        if (!users.containsKey(userId) || !devices.containsKey(deviceId)) {
            System.out.println("Ugyldig bruker-ID eller enhets-ID.");
            return;
        }

        UserDTO user = users.get(userId);
        DeviceDTO device = devices.get(deviceId);

        userDevices.get(userId).add(device);
        deviceUsers.get(deviceId).add(user);
        System.out.println("Enhet " + device.getType() + " tildelt til bruker " + user.getUsername());
    }

    private static void kontrollerEnhet(ControlDeviceUseCase controlDeviceUseCase, Scanner scanner) {
        System.out.print("Skriv inn enhets-ID: ");
        String deviceId = scanner.nextLine();

        if (!devices.containsKey(deviceId)) {
            System.out.println("Enhets-ID ikke funnet.");
            return;
        }

        System.out.println("1. Skru på");
        System.out.println("2. Skru av");
        System.out.print("Velg et alternativ: ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        boolean aktiv = valg == 1;
        controlDeviceUseCase.controlDevice(deviceId, aktiv);
        String status = aktiv ? "aktivert" : "deaktivert";
        System.out.println("Enhet " + deviceId + " er nå " + status + ".");
    }

    private static void visBrukereOgEnheter() {
        for (Map.Entry<String, UserDTO> entry : users.entrySet()) {
            System.out.println("Bruker: " + entry.getValue().getUsername() + " (ID: " + entry.getKey() + ")");
            List<DeviceDTO> devicesForUser = userDevices.get(entry.getKey());
            if (devicesForUser.isEmpty()) {
                System.out.println("Ingen enheter tildelt.");
            } else {
                devicesForUser.forEach(device -> System.out.println(" - Enhet: " + device.getType()));
            }
        }
    }

    private static void visEnheterOgBrukere() {
        for (Map.Entry<String, DeviceDTO> entry : devices.entrySet()) {
            System.out.println("Enhet: " + entry.getValue().getType() + " (ID: " + entry.getKey() + ")");
            List<UserDTO> usersForDevice = deviceUsers.get(entry.getKey());
            if (usersForDevice.isEmpty()) {
                System.out.println("Ingen brukere tilknyttet.");
            } else {
                usersForDevice.forEach(user -> System.out.println(" - Bruker: " + user.getUsername()));
            }
        }
    }

    private static void sendForhandsdefinertVarsel(SendNotificationUseCase notificationUseCase, Scanner scanner) {
        System.out.print("Skriv inn melding: ");
        String melding = scanner.nextLine();

        Notification notification = new PremadeNotification(UUID.randomUUID().toString(), melding, "bruker@eksempel.no");
        notificationUseCase.execute(notification);
        System.out.println("Forhåndsdefinert varsel sendt.");
    }

    private static void opprettOgSendTilpassetVarsel(SendNotificationUseCase notificationUseCase, Scanner scanner) {
        System.out.print("Skriv inn melding: ");
        String melding = scanner.nextLine();

        Notification notification = new CustomNotification(UUID.randomUUID().toString(), melding, "bruker@eksempel.no", LocalDateTime.now().plusMinutes(10));
        notificationUseCase.execute(notification);
        System.out.println("Tilpasset varsel opprettet og sendt.");
    }

    private static void visAlleVarsler(NotificationRepositoryStub repository) {
        List<Notification> notifications = repository.findAll();
        if (notifications.isEmpty()) {
            System.out.println("Ingen varsler funnet.");
        } else {
            notifications.forEach(notification -> System.out.println("Varsel: " + notification.getMessage()));
        }
    }

    private static void tomAlleVarsler(NotificationRepositoryStub repository) {
        repository.clearAll();
        System.out.println("Alle varsler er slettet.");
    }

    private static void avslutt() {
        System.out.println("Avslutter programmet.");
        System.exit(0);
    }
}
