package io.github.brunnotoscano.feignclient.rest.controller;


import io.github.brunnotoscano.feignclient.client.response.ErrorResponse;
import io.github.brunnotoscano.feignclient.exception.ClienteInvalidoException;
import io.github.brunnotoscano.feignclient.exception.ClienteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//|************************************************************************************|
//| Descrição: Trata as exceptions dadas pelo CustomErrorDecoder e retorna o erro para |
//| o usuário                                                                          |
//|************************************************************************************|

@RestControllerAdvice
public class ClienteControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex){
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleClienteInvalidoException(ClienteInvalidoException ex){
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
