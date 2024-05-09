package io.github.brunnotoscano.feignclient.exception;

//|***********************************************|
//| Descrição: Exception para Cliente inexistente |
//|***********************************************|
public class ClienteNaoEncontradoException extends RuntimeException{

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
