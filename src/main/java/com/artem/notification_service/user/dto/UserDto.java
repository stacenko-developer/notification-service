package com.artem.notification_service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Schema
public class UserDto implements Serializable {
    private static final long serialVersionUID = -4165529431441025153L;

    @Schema(description = "Идентификатор пользователя", example = "61f0c404-5cb3-11e7-907b-a6006ad3dba0")
    private UUID id;

    @Schema(description = "Логин пользователя", example = "artem")
    private String login;
}
