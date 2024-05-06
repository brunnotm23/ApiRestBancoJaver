package io.github.brunnotoscano.feignclient.client;

import io.github.brunnotoscano.feignclient.client.response.ClienteResponse;
import io.github.brunnotoscano.feignclient.config.ClienteClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "Cliente", url = "http://localhost:8080/api/clientes", configuration = ClienteClientConfiguration.class)
public interface ClienteClient {

    @GetMapping("/{id}")
    public ClienteResponse getClienteById(@PathVariable Integer id);

    @GetMapping("/buscar")
    public List<ClienteResponse> buscar(@SpringQueryMap ClienteResponse filtro);

    @PostMapping
    public ClienteResponse salvar (@RequestBody ClienteResponse cliente);

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id);

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody ClienteResponse cliente);



}
