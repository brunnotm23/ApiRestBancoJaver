package io.github.brunnotoscano.feignclient.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;


//|*********************************************|
//| Descrição: Template da classe Cliente local |
//|*********************************************|
@Data
public class Cliente {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer clientId;
    private String name;
    private Long phoneNumber;
    private Boolean currentAccount;
    private float creditScore;
    private float balance;
}
