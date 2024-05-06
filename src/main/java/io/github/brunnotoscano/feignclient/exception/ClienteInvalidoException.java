package io.github.brunnotoscano.feignclient.exception;

public class ClienteInvalidoException extends RuntimeException{

    public ClienteInvalidoException(String message){
        super(message);
    }
}
