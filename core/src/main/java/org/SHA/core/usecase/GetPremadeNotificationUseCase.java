package org.SHA.core.usecase;
import org.SHA.core.domain.Notification;
import org.SHA.core.domain.PremadeNotification;
import java.util.ArrayList;
import java.util.List;

public class GetPremadeNotificationUseCase {

    public List<Notification> execute() {
        List<Notification> premadeNotifications = new ArrayList<>();
        premadeNotifications.add(new PremadeNotification("001", "Husk å ta ut søpla!", "bruker@eksempel.no"));
        premadeNotifications.add(new PremadeNotification("002", "Komfyren står fortsatt på!", "bruker@eksempel.no"));
        premadeNotifications.add(new PremadeNotification("003", "Vaskemaskinen er ferdig.", "bruker@eksempel.no"));
        return premadeNotifications;
    }
}
