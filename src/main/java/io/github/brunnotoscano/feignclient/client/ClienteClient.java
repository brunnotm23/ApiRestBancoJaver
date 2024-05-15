package io.github.brunnotoscano.feignclient.client;

import io.github.brunnotoscano.feignclient.entity.Cliente;
import io.github.brunnotoscano.feignclient.config.ClienteClientConfiguration;
import io.github.brunnotoscano.feignclient.entity.ClienteDto;
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
    public ClienteDto getClienteById(@PathVariable Integer id);

    @GetMapping("/buscar")
    public List<ClienteDto> buscar(@SpringQueryMap ClienteDto filtro);

    @PostMapping
    public ClienteDto salvar (@RequestBody ClienteDto cliente);

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id);

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody ClienteDto cliente);



}
