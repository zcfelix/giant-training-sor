package com.thoughtworks.felix.training.picture.interfaces.rest;

import com.thoughtworks.felix.training.picture.interfaces.rest.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Arrays.asList;

public class NotFoundException extends InternalException {
    public NotFoundException(ErrorDTO... errors) {
        super(asList(errors));
    }

    public NotFoundException(List<? extends ErrorDTO> errors) {
        super(errors);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
