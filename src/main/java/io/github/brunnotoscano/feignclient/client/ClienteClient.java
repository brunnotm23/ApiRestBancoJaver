package io.github.brunnotoscano.feignclient.client;

import io.github.brunnotoscano.feignclient.client.response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ClienteClient", url = "http://localhost:8080/api/clientes")
public interface ClienteClient {

    @GetMapping("/{id}")
    public ClienteResponse getClienteById(@PathVariable Integer id);

    @GetMapping("/buscar")
    public List<ClienteResponse> buscar(@SpringQueryMap ClienteResponse filtro);

    @PostMapping
    public ClienteResponse salvar (@RequestBody @Validated ClienteResponse cliente);

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id);

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody @Validated ClienteResponse cliente);



}
