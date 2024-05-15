package io.github.brunnotoscano.feignclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

//|*************************************************************************|
//| Descrição: Template de resposta da aplicação no caso de erros previstos |
//|*************************************************************************|
@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;

    @JsonProperty(value = "mensagem")
    private String mensagemErro;
}
