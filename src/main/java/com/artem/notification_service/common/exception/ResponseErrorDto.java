package com.artem.notification_service.common.exception;

import com.artem.notification_service.common.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;

@Getter
@Setter
public class ResponseErrorDto extends ResponseDto {

    @Serial
    private static final long serialVersionUID = 5176965618930263954L;

    private String description;

    public ResponseErrorDto(String errCode, String description) {
        super(errCode);
        this.description = description;
    }
}
