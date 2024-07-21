package com.artem.notification_service.notification.exception;


import com.artem.notification_service.common.exception.AbstractException;
import java.io.Serial;

public class IncorrectNotificationException extends AbstractException {

    @Serial
    private static final long serialVersionUID = 7740741759986841390L;

    @Override
    public String getMessage() {
        return "INCORRECT_NOTIFICATION";
    }

    @Override
    public String getDescription() {
        return "Некорректное уведомление";
    }
}
