package me.timur.travelcompanyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class GroupAccessDeniedException extends BaseException {
    public GroupAccessDeniedException(String message) {
        super(message);
    }

    public GroupAccessDeniedException(Throwable throwable) {
        super(throwable);
    }

    public GroupAccessDeniedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
