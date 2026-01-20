package br.com.stock_control_back.dto;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

public record ApiResponse<D>(
        String status,
        int code,
        D data,
        String message) {

    public ApiResponse(ResponseStatusType status, HttpStatusCode code, D data, String message){
        this(status.getStatus(), code.value(), data, message);
    }

    @Getter
    public enum ResponseStatusType {
        SUCCESS("success"),
        ERROR("error");

        private final String status;

        ResponseStatusType(String status) {
            this.status = status;
        }

    }

}
