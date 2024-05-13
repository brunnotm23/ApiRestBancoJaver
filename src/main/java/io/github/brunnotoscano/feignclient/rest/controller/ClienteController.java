package io.github.brunnotoscano.feignclient.rest.controller;

import io.github.brunnotoscano.feignclient.client.ClienteClient;
import io.github.brunnotoscano.feignclient.client.response.Cliente;
import io.github.brunnotoscano.feignclient.client.response.ErrorResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

//|*****************************************************|
//| Descrição: Controller que expõe os endpoints da API |
//|*****************************************************|

@OpenAPIDefinition(info = @Info(title = "Banco Javer API",
        description = "API REST do Banco Javer que expõe os endpoints da base de dados",
        version = "1.0",
        contact = @Contact(name = "Brunno Toscano",
                            url = "https://github.com/brunnotm23",
                            email = "brunnotm23@gmail.com")
        )
)

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private final ClienteClient clienteClient;


    @Operation(summary = "Buscar Cliente por ID", description = "Endpoint GET de um Cliente pelo ID fornecido",
            responses = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteClient.getClienteById(id);
    }

    @Operation(summary = "Buscar Cliente", description = "Busca clientes baseado nos parâmetros fornecidos",
            responses = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.")
            }
    )
    @GetMapping("/buscar")
    public List<Cliente> buscar(@SpringQueryMap Cliente filtro){
        return clienteClient.buscar(filtro);
    }

    @Operation(summary = "Criar Cliente", description = "Cria um cliente com as informações fornecidas",
            responses = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações fornecidas são inválidas.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
            }
    )
    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteClient.salvar(cliente);
    }

    @Operation(summary = "Deletar Cliente", description = "Deleta o cliente que possuir o ID fornecido",
            responses = {
            @ApiResponse(responseCode = "204", description = "Operação realizada."),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        clienteClient.deletar(id);
    }

    @Operation(summary = "Atualizar Cliente", description = "Atualiza um cliente com as informações fornecidas",
            responses = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
                @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                        content = {@Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))}
                ),
            @ApiResponse(responseCode = "400", description = "Informações inválidas.")
            }
    )
    @PutMapping("/{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        clienteClient.atualizar(id, cliente);
    }

    @Operation(summary = "Calcular e Salvar Score", description = "Calcula e salva o score do cliente fornecido",
            responses = {
            @ApiResponse(responseCode = "200", description = "Score calculado e salvo com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @PatchMapping("/score/{id}")
    public float calculaScore(@PathVariable Integer id){
        Cliente cliente = getClienteById(id);
        float score = (float) (cliente.getSaldo_cc() * 0.1);
        cliente.setScore_credito(score);
        salvar(cliente);
        return score;
    }


}
