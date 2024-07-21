package com.artem.notification_service.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class PagingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7655789342892835886L;

    @Schema(description = "Номер страницы", example = "0")
    private int page;

    @Schema(description = "Размер страницы", example = "2")
    private int size;

    public PageRequest getPageRequest() {
        if (size == 0) {
            return null;
        }

        int defaultPageNumber = 0;

        if (page < 0) {
            this.page = defaultPageNumber;
        }

        int defaultPageSize = 0;

        if (size < 0) {
            this.size = defaultPageSize;
        }

        return PageRequest.of(page, size);
    }
}
