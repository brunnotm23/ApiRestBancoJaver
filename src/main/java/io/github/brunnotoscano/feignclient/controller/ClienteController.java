package io.github.brunnotoscano.feignclient.controller;

import io.github.brunnotoscano.feignclient.client.ClienteClient;
import io.github.brunnotoscano.feignclient.client.response.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteClient clienteClient;

    @GetMapping("/{id}")
    public ClienteResponse getClienteById(@PathVariable Integer id){
        return clienteClient.getClienteById(id);
    }

    @GetMapping("/buscar")
    public List<ClienteResponse> buscar(@SpringQueryMap ClienteResponse filtro){
        return clienteClient.buscar(filtro);
    }

    @PostMapping
    public ClienteResponse salvar (@RequestBody ClienteResponse cliente){
        return clienteClient.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        clienteClient.deletar(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody ClienteResponse cliente){
        clienteClient.atualizar(id, cliente);
    }

    @PatchMapping("/score/{id}")
    public float calculaScore(@PathVariable Integer id){
        ClienteResponse cliente = getClienteById(id);
        float score = (float) (cliente.getSaldo_cc() * 0.1);
        cliente.setScore_credito(score);
        salvar(cliente);
        return score;
    }


}
