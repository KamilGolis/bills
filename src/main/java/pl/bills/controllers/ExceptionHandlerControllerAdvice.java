package pl.bills.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Created by trot on 13.04.17.
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        LOGGER.error("No such element - " + e.getMessage());
        return "home";  //e.getMessage();
    }

}
