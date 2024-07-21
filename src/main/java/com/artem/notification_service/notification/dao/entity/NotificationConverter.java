package com.artem.notification_service.notification.dao.entity;

import com.artem.notification_service.notification.dto.NotificationDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class NotificationConverter {
    private final ModelMapper modelMapper;

    public NotificationConverter() {
        this.modelMapper = new ModelMapper();
    }

    public NotificationDto convertToDto(Notification notification) {
        return modelMapper.map(notification, NotificationDto.class);
    }

    public NotificationDto convertToDto(Notification notification, UUID userId) {
        NotificationDto notificationDto = convertToDto(notification);

        NotificationView notificationView = notification.getNotificationViews()
                .stream()
                .filter(view -> view.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        notificationDto.setRead(notificationView != null);

        return notificationDto;
    }

    public Notification convertToModel(NotificationDto notificationDto) {
        return modelMapper.map(notificationDto, Notification.class);
    }

    public void map(NotificationDto notificationDto, Notification notification) {
        modelMapper.map(notificationDto, notification);
    }
}
