package io.github.brunnotoscano.feignclient.service;

import io.github.brunnotoscano.feignclient.entity.Cliente;
import io.github.brunnotoscano.feignclient.entity.ClienteDto;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClienteService {

    public Cliente getClienteById(Integer id);
    public List<Cliente> buscar(Cliente filtro);
    public Cliente salvar (Cliente cliente);
    public void deletar(Integer id);
    public void atualizar(Integer id, Cliente cliente);
    public float calculaScore(Integer id);
}
