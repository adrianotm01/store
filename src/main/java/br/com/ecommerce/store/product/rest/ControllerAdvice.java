package br.com.ecommerce.store.product.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ecommerce.store.error.ErrorDTO;
import br.com.ecommerce.store.exception.NotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ErrorDTO> notFoundExceptionHandler(NotFoundException notFoundException){
        ErrorDTO errorDTO = new ErrorDTO(notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllUncaughtException( Exception exception){
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

}
