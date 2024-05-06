package io.github.brunnotoscano.feignclient.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;

    @JsonProperty(value = "mensagem")
    private String mensagemErro;
}
