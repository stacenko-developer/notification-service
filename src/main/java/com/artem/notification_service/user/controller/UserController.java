package com.artem.notification_service.user.controller;

import com.artem.notification_service.common.PagingDto;
import com.artem.notification_service.user.bean.UserServiceBean;
import com.artem.notification_service.user.dto.UserDto;
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
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserServiceBean userServiceBean;

    @PostMapping("/search")
    @Operation(
           summary = "Получить всех пользователей",
            description = "Получение всех пользователей"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                    ),
                    description = "Возвращает список пользователей"
            )
    })
    public ResponseEntity<?> getAllUsers(
            @Parameter(description = "Пагинация", required = true) @RequestBody PagingDto pagingDto) {
        return new ResponseEntity<>(userServiceBean.getAllUsers(pagingDto), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @Operation(
            summary = "Получить пользователя по идентификатору",
            description = "Получение пользователя по идентификатору"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    ),
                    description = "Возвращает пользователя по идентификатору"
            )
    })
    public ResponseEntity<?> getUserById(
            @Parameter(description = "Идентификатор заявки", required = true) @PathVariable UUID id
    ) {
        return new ResponseEntity<>(userServiceBean.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Создать пользователя",
            description = "Создание пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    ),
                    description = "Возвращает созданного пользователя"
            )
    })
    public ResponseEntity<?> createUser(
            @Parameter(description = "Объект пользователя") @RequestBody UserDto user
    ) {
        return new ResponseEntity<>(userServiceBean.createUser(user), HttpStatus.OK);
    }

    @PutMapping
    @Operation(
            summary = "Обновить данные пользователя",
            description = "Обновление данных пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    ),
                    description = "Возвращает обновленного пользователя"
            )
    })
    public ResponseEntity<?> updateUser(
            @Parameter(description = "Объект пользователя") @RequestBody UserDto user
    ) {
        return new ResponseEntity<>(userServiceBean.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    @Operation(
            summary = "Удалить пользователя",
            description = "Удаление пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Возвращает 200, если удаление прошло успешно"
            )
    })
    public ResponseEntity<?> deleteUser(
            @Parameter(description = "Идентификатор пользователя", required = true) @PathVariable UUID id
    ) {
        userServiceBean.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}