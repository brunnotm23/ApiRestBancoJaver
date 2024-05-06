package io.github.brunnotoscano.feignclient.client.response;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClienteResponse {

    private Integer id;

    @NotEmpty(message = "Campo nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "Campo telefone não pode ser vazio.")
    private Long telefone;

    @NotNull(message = "Campo correntista não pode ser vazio.")
    private Boolean correntista;

    private float score_credito;
    private float saldo_cc;
}
