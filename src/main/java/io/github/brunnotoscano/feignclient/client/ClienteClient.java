package io.github.brunnotoscano.feignclient.client;

import io.github.brunnotoscano.feignclient.client.response.Cliente;
import io.github.brunnotoscano.feignclient.config.ClienteClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//|**********************************************************|
//| Descrição: Cliente Feign que conecta com a base de dados |
//|**********************************************************|

@FeignClient(name = "Cliente", url = "http://localhost:8080/api/clientes", configuration = ClienteClientConfiguration.class)
public interface ClienteClient {

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id);

    @GetMapping("/buscar")
    public List<Cliente> buscar(@SpringQueryMap Cliente filtro);

    @PostMapping
    public Cliente salvar (@RequestBody Cliente cliente);

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id);

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente cliente);



}
