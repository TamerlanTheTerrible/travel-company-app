package me.timur.travelcompanyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Temurbek Ismoilov on 25/03/22.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpecificationBuilderException extends BaseException {
    public SpecificationBuilderException(String message) {
        super(message);
    }

    public SpecificationBuilderException(Throwable throwable) {
        super(throwable);
    }

    public SpecificationBuilderException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
