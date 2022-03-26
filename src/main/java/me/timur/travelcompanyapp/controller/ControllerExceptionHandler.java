package me.timur.travelcompanyapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import me.timur.travelcompanyapp.exception.GroupAccessDeniedException;
import me.timur.travelcompanyapp.exception.InvalidInputException;
import me.timur.travelcompanyapp.exception.SpecificationBuilderException;
import me.timur.travelcompanyapp.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    public BaseResponse handleInvalidInputException(InvalidInputException e){
        log.error(e.getMessage());
        return BaseResponse.error(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SpecificationBuilderException.class)
    public BaseResponse handleSpecificationBuilderException(SpecificationBuilderException e){
        log.error(e.getMessage());
        return BaseResponse.error(e);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(GroupAccessDeniedException.class)
    public BaseResponse handleGroupAccessDeniedException(GroupAccessDeniedException e){
        log.error(e.getMessage());
        return BaseResponse.error(e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JsonProcessingException.class)
    public BaseResponse handleJsonProcessingException(JsonProcessingException e) {
        log.error(e.getMessage(), e);
        return BaseResponse.error(e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handleUnexpectedException(Exception e){
        log.error("Unexpected exception: " + e.getMessage(), e);
        return BaseResponse.error(e);
    }

}



