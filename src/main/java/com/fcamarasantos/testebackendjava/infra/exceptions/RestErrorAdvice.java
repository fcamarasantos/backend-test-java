package com.fcamarasantos.testebackendjava.infra.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class RestErrorAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var validationErrorsList = exception
                .getFieldErrors()
                .stream()
                .map(ValidationErrorMessage::new)
                .toList();

        return ResponseEntity
                .badRequest()
                .body(validationErrorsList);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> HttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .badRequest()
                .body(new NotReadableErrorMessage(exception));
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<?> validationException(BusinessValidationException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity serverInternalError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    private record ValidationErrorMessage(String field, String message) {
        ValidationErrorMessage(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    private record NotReadableErrorMessage(String message) {
        NotReadableErrorMessage(HttpMessageNotReadableException exception) {
            this(exception.getMessage());
        }
    }

    private record ErrorMessage(String message) {
    }
}
