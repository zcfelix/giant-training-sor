package com.thoughtworks.felix.training.picture.interfaces.rest;

import com.thoughtworks.felix.training.picture.interfaces.rest.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class InternalException extends RuntimeException {
    private List<? extends ErrorDTO> errors;

    public InternalException(List<? extends ErrorDTO> errors) {
        super();
        this.errors = errors;
    }

    public List<? extends ErrorDTO> getErrors() {
        return errors;
    }

    public abstract HttpStatus httpStatus();

    public boolean is4xxFailure() {
        return httpStatus().is4xxClientError();
    }

    public boolean is5xxFailure() {
        return httpStatus().is5xxServerError();
    }
}
