package com.example.desafio_luizalabs.config;

import com.example.desafio_luizalabs.dtos.ExceptionDTO;
import com.example.desafio_luizalabs.exception.ClientNotFoundException;
import com.example.desafio_luizalabs.exception.InvalidCredentialsException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> handleDuplicatedEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> handle404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGeneralException(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidCredentialsException(InvalidCredentialsException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleClientNotFoundException(ClientNotFoundException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionDTO> handleEntityExistsException(EntityExistsException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
