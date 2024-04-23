package io.github.brunnotoscano.feignclient.client.response;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClienteResponse {

    private Integer id;
    private String nome;
    private Long telefone;
    private boolean correntista;
    private float score_credito;
    private float saldo_cc;
}
