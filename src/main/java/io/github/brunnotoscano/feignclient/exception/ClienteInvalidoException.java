package io.github.brunnotoscano.feignclient.exception;

//|**************************************************************|
//| Descrição: Exception para Clientes com informações inválidas |
//|**************************************************************|
public class ClienteInvalidoException extends RuntimeException{

    public ClienteInvalidoException(String message){
        super(message);
    }
}
