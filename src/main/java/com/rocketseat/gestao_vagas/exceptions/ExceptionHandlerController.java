package com.rocketseat.gestao_vagas.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessageDto> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
         String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
         ErrorMessageDto error = new ErrorMessageDto(message, err.getField());
         dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseError(HttpMessageNotReadableException e) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto("Invalid JSON format", "There was an error parsing your request. Please ensure your JSON is correctly formatted.");
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponseDto> handleTokenExpiredException(TokenExpiredException e ){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getCause().toString(), e.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.UNAUTHORIZED);

    }

}




