package io.github.brunnotoscano.feignclient.client.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



//|**********************************************************************|
//| Descrição: Template da classe Cliente recebida e enviada ao servidor |
//|**********************************************************************|
@Data
public class Cliente {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotNull
    private Long telefone;

    @NotNull
    private Boolean correntista;

    private float score_credito;
    private float saldo_cc;
}
