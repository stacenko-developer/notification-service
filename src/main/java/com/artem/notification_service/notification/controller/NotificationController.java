package com.artem.notification_service.notification.controller;

import com.artem.notification_service.notification.bean.NotificationServiceBean;
import com.artem.notification_service.notification.dto.NotificationDto;
import com.artem.notification_service.notification.dto.SearchNotificationsDto;
import com.artem.notification_service.notification.dto.UpdateNotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationServiceBean notificationServiceBean;

    @PostMapping("/search")
    @Operation(
            summary = "Получить все уведомления",
            description = "Получение всех уведомлений"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = NotificationDto.class))
                    ),
                    description = "Возвращает список уведомлений"
            )
    })
    public ResponseEntity<?> getAllNotifications(
            @Parameter(description = "Request dto", required = true)
            @RequestBody SearchNotificationsDto searchNotificationsDto
    ) {
        return new ResponseEntity<>(notificationServiceBean
                .getAllNotifications(searchNotificationsDto), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать уведомления",
            description = "Создание уведомления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NotificationDto.class)
                    ),
                    description = "Возвращает созданное уведомление"
            )
    })
    public ResponseEntity<?> createNotification(@RequestBody NotificationDto notification) {
        return new ResponseEntity<>(notificationServiceBean.createNotification(notification), HttpStatus.OK);
    }

    @PutMapping
    @Operation(
            summary = "Обновить данные уведомления",
            description = "Обновление данных уведомления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NotificationDto.class)
                    ),
                    description = "Возвращает обновленноe уведомления"
            )
    })
    public ResponseEntity<?> updateNotification(
            @Parameter(description = "Объект уведомления") @RequestBody UpdateNotificationDto updateNotificationDto
    ) {
        return new ResponseEntity<>(notificationServiceBean
                .updateNotification(updateNotificationDto), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    @Operation(
            summary = "Удалить уведомление",
            description = "Удаление уведомления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возвращает 200, если удаление прошло успешно"
            )
    })
    public ResponseEntity<?> deleteNotification(
            @Parameter(description = "Идентификатор уведомления") @PathVariable UUID id
    ) {
        notificationServiceBean.deleteNotification(id);
        return ResponseEntity.ok().build();
    }
}
