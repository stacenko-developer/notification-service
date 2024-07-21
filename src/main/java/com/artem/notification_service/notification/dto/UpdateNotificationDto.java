package com.artem.notification_service.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Schema
public class UpdateNotificationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5303566610375571221L;

    @Schema(description = "Идентификатор пользователя", example = "61f0c404-5cb3-11e7-907b-a6006ad3dba0")
    private UUID userId;

    @Schema(description = "Уведомление, данные которого необходимо обновить")
    private NotificationDto notificationDto;
}
