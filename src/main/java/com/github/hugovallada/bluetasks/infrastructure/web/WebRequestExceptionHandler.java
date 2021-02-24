package com.github.hugovallada.bluetasks.infrastructure.web;

import com.github.hugovallada.bluetasks.domain.task.DuplicateTaskException;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRequestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleException(RepositoryConstraintViolationException exception) {
        return RestResponseError.fromValidationError(exception.getErrors());
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleException(DuplicateTaskException exception) {
        return RestResponseError.fromMessage(exception.getMessage());
    }

}
