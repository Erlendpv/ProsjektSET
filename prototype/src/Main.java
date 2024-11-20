
import org.SHA.core.usecase.ControlDeviceUseCase;

public class Main {
    public static void main(String[] args) {
        // Opprett en instans av ControlDeviceUseCase
        ControlDeviceUseCase controlDeviceUseCase = new ControlDeviceUseCase();

        // Demonstrer hvordan du sender en kommando til enheten
        String deviceId = "device123";
        String command = "turn on";
        controlDeviceUseCase.executeCommand(deviceId, command);

        // Demonstrer hvordan du henter statusen til en enhet
        String status = controlDeviceUseCase.retrieveDeviceStatus(deviceId);
        System.out.println(status);
    }
}
