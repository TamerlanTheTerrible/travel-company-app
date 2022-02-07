package me.timur.travelcompanyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidUsernameException extends BaseException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
