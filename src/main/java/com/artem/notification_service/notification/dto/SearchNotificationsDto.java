package com.artem.notification_service.notification.dto;

import com.artem.notification_service.common.PagingDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.util.UUID;

@Getter
@Setter
@Schema
public class SearchNotificationsDto extends PagingDto {
    @Serial
    private static final long serialVersionUID = -5491558031315062182L;

    @Schema(description = "Статус прочтения", example = "true")
    private Boolean isRead;

    @Schema(description = "Идентификатор пользователя", example = "61f0c404-5cb3-11e7-907b-a6006ad3dba0")
    private UUID userId;
}
