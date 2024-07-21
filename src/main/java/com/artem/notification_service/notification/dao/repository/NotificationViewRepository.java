package com.artem.notification_service.notification.dao.repository;

import com.artem.notification_service.notification.dao.entity.NotificationView;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NotificationViewRepository extends JpaRepository<NotificationView, UUID> {
    Optional<NotificationView> findByUserIdAndNotificationId(UUID userId, UUID notificationId);
}
