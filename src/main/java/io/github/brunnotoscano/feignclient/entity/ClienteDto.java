package io.github.brunnotoscano.feignclient.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDto {

    private Integer id;
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float score_credito;
    private float saldo_cc;

}
