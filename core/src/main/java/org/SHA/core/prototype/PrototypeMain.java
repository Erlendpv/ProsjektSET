package org.SHA.core.prototype;
import org.SHA.core.domain.CustomNotification;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import org.SHA.core.dto.DeviceDTO;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.NotificationRepositoryStub;
import org.SHA.core.port.NotificationService;

import java.time.LocalDateTime;
import java.util.*;

public class PrototypeMain {

    private static final Map<String, UserDTO> users = new HashMap<>(); // Brukere med bruker-ID som nøkkel
    private static final Map<String, DeviceDTO> devices = new HashMap<>(); // Enheter med enhets-ID som nøkkel
    private static final Map<String, List<DeviceDTO>> userDevices = new HashMap<>(); // Enheter koblet til brukere
    private static final Map<String, List<UserDTO>> deviceUsers = new HashMap<>(); // Brukere koblet til enheter

    public static void main(String[] args) {
        NotificationRepositoryStub repository = new NotificationRepositoryStub();
        NotificationService service = new NotificationService(repository);

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
                case 6 -> sendForhandsdefinertVarsel(service, scanner);
                case 7 -> opprettOgSendTilpassetVarsel(service, scanner);
                case 8 -> visAlleVarsler(service);
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
        userDevices.put(id, new ArrayList<>()); // Initialiser en tom liste med enheter for denne brukeren
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
        deviceUsers.put(deviceId, new ArrayList<>()); // Initialiser en tom liste med brukere for denne enheten
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
        for (String userId : users.keySet()) {
            UserDTO user = users.get(userId);
            System.out.println("Bruker-ID: " + user.getUserId());
            System.out.println("Navn: " + user.getUsername());
            System.out.println("E-post: " + user.getEmail());
            List<DeviceDTO> devices = userDevices.get(userId);
            if (devices.isEmpty()) {
                System.out.println("  Ingen enheter koblet til denne brukeren.");
            } else {
                System.out.println("  Tilkoblede enheter:");
                for (DeviceDTO device : devices) {
                    System.out.println("    Enhets-ID: " + device.getDeviceId() + ", Type: " + device.getType() + ", Status: " + device.getStatus());
                }
            }
            System.out.println("---------------------");
        }
    }

    private static void visEnheterOgBrukere() {
        if (devices.isEmpty()) {
            System.out.println("Ingen enheter funnet.");
            return;
        }

        System.out.println("\n--- Enheter og deres brukere ---");
        for (String deviceId : devices.keySet()) {
            DeviceDTO device = devices.get(deviceId);
            System.out.println("Enhets-ID: " + device.getDeviceId());
            System.out.println("Type: " + device.getType());
            System.out.println("Status: " + device.getStatus());
            List<UserDTO> usersForDevice = deviceUsers.get(deviceId);
            if (usersForDevice.isEmpty()) {
                System.out.println("  Ingen brukere koblet til denne enheten.");
            } else {
                System.out.println("  Tilkoblede brukere:");
                for (UserDTO user : usersForDevice) {
                    System.out.println("    Bruker-ID: " + user.getUserId() + ", Navn: " + user.getUsername());
                }
            }
            System.out.println("---------------------");
        }
    }

    private static void sendForhandsdefinertVarsel(NotificationService service, Scanner scanner) {
        if (users.isEmpty()) {
            System.out.println("Ingen brukere tilgjengelig. Legg til en bruker først.");
            return;
        }

        System.out.print("Velg bruker-ID for mottaker: ");
        String brukerId = scanner.nextLine();
        if (!users.containsKey(brukerId)) {
            System.out.println("Bruker-ID ikke funnet.");
            return;
        }

        Notification notification = new PremadeNotification("001", "Dette er en testmelding!", brukerId);
        service.sendNotification(notification);
        System.out.println("Forhåndsdefinert varsel sendt til bruker: " + users.get(brukerId).getUsername());
    }

    private static void opprettOgSendTilpassetVarsel(NotificationService service, Scanner scanner) {
        if (users.isEmpty()) {
            System.out.println("Ingen brukere tilgjengelig. Legg til en bruker først.");
            return;
        }

        System.out.print("Velg bruker-ID for mottaker: ");
        String brukerId = scanner.nextLine();
        if (!users.containsKey(brukerId)) {
            System.out.println("Bruker-ID ikke funnet.");
            return;
        }

        System.out.print("Skriv inn melding: ");
        String melding = scanner.nextLine();

        LocalDateTime scheduledTime = LocalDateTime.now().plusMinutes(10);

        Notification notification = new CustomNotification("002", melding, brukerId, scheduledTime);
        service.sendNotification(notification);
        System.out.println("Tilpasset varsel sendt til bruker: " + users.get(brukerId).getUsername());
    }

    private static void visAlleVarsler(NotificationService service) {
        List<Notification> notifications = service.getAllNotifications();
        if (notifications.isEmpty()) {
            System.out.println("Ingen varsler funnet.");
        } else {
            System.out.println("\n--- Lagrede varsler ---");
            for (Notification notification : notifications) {
                System.out.println("ID: " + notification.getNotificationId());
                System.out.println("Melding: " + notification.getMessage());
                System.out.println("Mottaker: " + notification.getRecipient());
                System.out.println("Tidspunkt: " + notification.getTimestamp());
                System.out.println("-----------------------");
            }
        }
    }

    private static void tomAlleVarsler(NotificationRepositoryStub repository) {
        repository.clearAll();
        System.out.println("Alle varsler er slettet.");
    }

    private static void avslutt() {
        System.out.println("Avslutter programmet. Ha en fin dag!");
        System.exit(0);
    }
}
