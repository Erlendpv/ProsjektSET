package org.SHA.core.port.repository;
import org.SHA.core.domain.Notification;
import java.util.List;

// Interface for håndtering av lagring og henting av varsler
public interface NotificationRepository {
    void save(Notification notification);
    List<Notification> findAll();
    void clearAll();
}
