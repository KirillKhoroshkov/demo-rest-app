package com.example.demorestapp.controller;

import com.example.demorestapp.exception.RecordNotFoundException;
import com.example.demorestapp.model.Error;
import com.example.demorestapp.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RestController
@RequestMapping("/errors")
public class ErrorController {

    private ErrorService errorService;

    @Autowired
    private void setErrorService(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Error fetchLastCreatedError() {
        return errorService.fetchLastCreatedError();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final Error handleRecordNotFoundException(RecordNotFoundException exception) {
        return createAndSaveError(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final Error handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return createAndSaveError(exception);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final Error handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Throwable mostSpecificCause = exception.getMostSpecificCause();
        return createAndSaveError(mostSpecificCause);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Error handleException(Exception exception) {
        return createAndSaveError(exception);
    }

    private Error createAndSaveError(Throwable throwable) {
        String message = throwable.getMessage();
        Error error = new Error();
        error.setMessage(message);
        error = errorService.saveError(error);
        return error;
    }
}