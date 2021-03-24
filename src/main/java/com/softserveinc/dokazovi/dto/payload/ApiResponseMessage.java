package com.softserveinc.dokazovi.dto.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseMessage {
    private boolean success;
    private String message;

    public ApiResponseMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
