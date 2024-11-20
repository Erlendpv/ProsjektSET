package org.SHA.core.prototype;
import org.SHA.core.domain.Notification;
import org.SHA.core.dto.DeviceDTO;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.repository.NotificationRepository;
import org.SHA.core.port.repository.NotificationRepositoryStub;
import org.SHA.core.usecase.CreateCustomNotificationUseCase;
import org.SHA.core.usecase.GetPremadeNotificationUseCase;

import java.time.LocalDateTime;
import java.util.*;

public class PrototypeMain {

    private static final Map<String, UserDTO> users = new HashMap<>(); // Brukere med bruker-ID som nøkkel
    private static final Map<String, DeviceDTO> devices = new HashMap<>(); // Enheter med enhets-ID som nøkkel
    private static final Map<String, List<DeviceDTO>> userDevices = new HashMap<>(); // Enheter koblet til brukere
    private static final Map<String, List<UserDTO>> deviceUsers = new HashMap<>(); // Brukere koblet til enheter

    public static void main(String[] args) {
        NotificationRepository repository = new NotificationRepositoryStub();
        CreateCustomNotificationUseCase customNotificationUseCase = new CreateCustomNotificationUseCase();
        GetPremadeNotificationUseCase premadeNotificationUseCase = new GetPremadeNotificationUseCase();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SmartHome Assistant Prototype ---");
            System.out.println("1. Legg til bruker");
            System.out.println("2. Opprett enhet");
            System.out.println("3. Tildel enhet til bruker");
            System.out.println("4. Vis brukere og deres enheter");
            System.out.println("5. Vis enheter og deres brukere");
            System.out.println("6. Send forhåndsdefinert varsel");
            System.out.println("7. Opprett og send tilpasset varsel");
            System.out.println("8. Vis alle varsler");
            System.out.println("9. Tøm alle varsler");
            System.out.println("10. Avslutt");
            System.out.print("Velg et alternativ: ");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> leggTilBruker(scanner);
                case 2 -> opprettEnhet(scanner);
                case 3 -> tildelEnhetTilBruker(scanner);
                case 4 -> visBrukereOgEnheter();
                case 5 -> visEnheterOgBrukere();
                case 6 -> sendForhandsdefinertVarsel(repository, premadeNotificationUseCase, scanner);
                case 7 -> opprettOgSendTilpassetVarsel(repository, customNotificationUseCase, scanner);
                case 8 -> visAlleVarsler(repository);
                case 9 -> tomAlleVarsler(repository);
                case 10 -> avslutt();
                default -> System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }

    private static void leggTilBruker(Scanner scanner) {
        System.out.print("Skriv inn bruker-ID: ");
        String id = scanner.nextLine();
        System.out.print("Skriv inn brukernavn: ");
        String navn = scanner.nextLine();
        System.out.print("Skriv inn e-post: ");
        String epost = scanner.nextLine();

        UserDTO user = new UserDTO(id, navn, epost, new ArrayList<>());
        users.put(id, user);
        userDevices.put(id, new ArrayList<>());
        System.out.println("Bruker lagt til: " + navn);
    }

    private static void opprettEnhet(Scanner scanner) {
        System.out.print("Skriv inn enhets-ID: ");
        String deviceId = scanner.nextLine();
        System.out.print("Skriv inn enhetstype: ");
        String type = scanner.nextLine();
        System.out.print("Skriv inn status (Aktiv/Inaktiv): ");
        String status = scanner.nextLine();

        DeviceDTO device = new DeviceDTO(deviceId, type, status);
        devices.put(deviceId, device);
        deviceUsers.put(deviceId, new ArrayList<>());
        System.out.println("Enhet opprettet: " + type + " (ID: " + deviceId + ")");
    }

    private static void tildelEnhetTilBruker(Scanner scanner) {
        if (devices.isEmpty() || users.isEmpty()) {
            System.out.println("Ingen brukere eller enheter tilgjengelig. Opprett først.");
            return;
        }

        System.out.print("Skriv inn enhets-ID: ");
        String deviceId = scanner.nextLine();
        if (!devices.containsKey(deviceId)) {
            System.out.println("Enhets-ID ikke funnet.");
            return;
        }

        System.out.print("Skriv inn bruker-ID for å tildele enheten: ");
        String userId = scanner.nextLine();
        if (!users.containsKey(userId)) {
            System.out.println("Bruker-ID ikke funnet.");
            return;
        }

        DeviceDTO device = devices.get(deviceId);
        UserDTO user = users.get(userId);

        userDevices.get(userId).add(device);
        deviceUsers.get(deviceId).add(user);
        System.out.println("Enhet " + device.getType() + " tildelt bruker " + user.getUsername());
    }

    private static void visBrukereOgEnheter() {
        if (users.isEmpty()) {
            System.out.println("Ingen brukere funnet.");
            return;
        }

        System.out.println("\n--- Brukere og deres enheter ---");
        for (Map.Entry<String, UserDTO> entry : users.entrySet()) {
            UserDTO user = entry.getValue();
            System.out.println("Bruker-ID: " + user.getUserId() + ", Navn: " + user.getUsername());
            List<DeviceDTO> devices = userDevices.get(entry.getKey());
            if (devices.isEmpty()) {
                System.out.println("  Ingen enheter koblet til.");
            } else {
                devices.forEach(device -> System.out.println("  Enhet: " + device.getType()));
            }
        }
    }

    private static void visEnheterOgBrukere() {
        if (devices.isEmpty()) {
            System.out.println("Ingen enheter funnet.");
            return;
        }

        System.out.println("\n--- Enheter og deres brukere ---");
        for (Map.Entry<String, DeviceDTO> entry : devices.entrySet()) {
            DeviceDTO device = entry.getValue();
            System.out.println("Enhets-ID: " + device.getDeviceId() + ", Type: " + device.getType());
            List<UserDTO> usersForDevice = deviceUsers.get(entry.getKey());
            if (usersForDevice.isEmpty()) {
                System.out.println("  Ingen brukere koblet til.");
            } else {
                usersForDevice.forEach(user -> System.out.println("  Bruker: " + user.getUsername()));
            }
        }
    }

    private static void sendForhandsdefinertVarsel(NotificationRepository repository, GetPremadeNotificationUseCase premadeNotificationUseCase, Scanner scanner) {
        List<Notification> premadeNotifications = premadeNotificationUseCase.execute();
        System.out.println("\nVelg et forhåndsdefinert varsel:");
        for (int i = 0; i < premadeNotifications.size(); i++) {
            System.out.println((i + 1) + ". " + premadeNotifications.get(i).getMessage());
        }
        int valg = scanner.nextInt() - 1;
        scanner.nextLine();
        if (valg < 0 || valg >= premadeNotifications.size()) {
            System.out.println("Ugyldig valg.");
            return;
        }

        Notification notification = premadeNotifications.get(valg);
        repository.save(notification);
        System.out.println("Varsel sendt: " + notification.getMessage());
    }

    private static void opprettOgSendTilpassetVarsel(NotificationRepository repository, CreateCustomNotificationUseCase customNotificationUseCase, Scanner scanner) {
        System.out.print("Skriv inn melding: ");
        String melding = scanner.nextLine();

        System.out.print("Skriv inn mottaker: ");
        String mottaker = scanner.nextLine();

        LocalDateTime scheduledTime = LocalDateTime.now().plusMinutes(10);
        Notification notification = customNotificationUseCase.create(UUID.randomUUID().toString(), melding, mottaker, scheduledTime);
        repository.save(notification);
        System.out.println("Tilpasset varsel opprettet og planlagt.");
    }

    private static void visAlleVarsler(NotificationRepository repository) {
        List<Notification> notifications = repository.findAll();
        if (notifications.isEmpty()) {
            System.out.println("Ingen varsler funnet.");
        } else {
            System.out.println("\n--- Lagrede varsler ---");
            notifications.forEach(notification -> System.out.println("Varsel: " + notification.getMessage()));
        }
    }

    private static void tomAlleVarsler(NotificationRepository repository) {
        repository.clearAll();
        System.out.println("Alle varsler slettet.");
    }

    private static void avslutt() {
        System.out.println("Avslutter programmet. Ha en fin dag!");
    }
}
