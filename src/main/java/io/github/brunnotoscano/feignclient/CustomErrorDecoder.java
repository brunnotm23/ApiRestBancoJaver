package io.github.brunnotoscano.feignclient;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.github.brunnotoscano.feignclient.exception.ClienteInvalidoException;
import io.github.brunnotoscano.feignclient.exception.ClienteNaoEncontradoException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new ClienteInvalidoException("Informações inválidas.");
            case 404:
                return new ClienteNaoEncontradoException("Cliente não encontrado");
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }

    }
}