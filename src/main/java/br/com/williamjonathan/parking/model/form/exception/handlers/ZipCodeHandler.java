package br.com.williamjonathan.parking.model.form.exception.handlers;

import br.com.williamjonathan.parking.config.handlers.ErrorDto;
import br.com.williamjonathan.parking.model.form.exception.CnpjDuplicateEntryException;
import br.com.williamjonathan.parking.model.form.exception.ZipCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ZipCodeHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ZipCodeException.class)
    public List<ErrorDto> handle(ZipCodeException exception) {
        List<ErrorDto> dto = new ArrayList<>();

        ErrorDto error = new ErrorDto("Zip Code", "This zip code is not valid");
        dto.add(error);
        return dto;
    }
}
