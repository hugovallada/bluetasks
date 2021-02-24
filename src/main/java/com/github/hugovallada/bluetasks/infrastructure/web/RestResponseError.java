package com.github.hugovallada.bluetasks.infrastructure.web;

import org.springframework.validation.Errors;

import lombok.Getter;

public class RestResponseError {
    
    @Getter
    private String error;

    private RestResponseError(){}

    public static RestResponseError fromValidationError(Errors errors) {
        var resp = new RestResponseError();
        var sb = new StringBuilder();

        for (var error : errors.getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(". ");
        }

        resp.error = sb.toString();
        return resp;
    }
}
