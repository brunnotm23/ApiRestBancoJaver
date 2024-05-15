package io.github.brunnotoscano.feignclient.service.impl;

import io.github.brunnotoscano.feignclient.client.ClienteClient;
import io.github.brunnotoscano.feignclient.entity.Cliente;
import io.github.brunnotoscano.feignclient.mapper.ClienteMapper;
import io.github.brunnotoscano.feignclient.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private final ClienteClient clienteClient;

    @Autowired
    private final ClienteMapper mapper;

    @Override
    public Cliente getClienteById(Integer id) {
        return mapper.clienteDtoToCliente(clienteClient.getClienteById(id));
    }

    @Override
    public List<Cliente> buscar(Cliente filtro) {
        return mapper.listClienteDtoToListCliente(clienteClient.buscar(
                mapper.clienteToClienteDto(filtro)));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return mapper.clienteDtoToCliente(clienteClient.salvar(mapper.clienteToClienteDto(cliente)));
    }

    @Override
    public void deletar(Integer id) {
        clienteClient.deletar(id);
    }

    @Override
    public void atualizar(Integer id, Cliente cliente) {
        clienteClient.atualizar(
                id,
                mapper.clienteToClienteDto(cliente)
        );
    }

    @Override
    public float calculaScore(Integer id){
        Cliente cliente = getClienteById(id);
        float score = (float) (cliente.getBalance() * 0.1);
        cliente.setCreditScore(score);
        salvar(cliente);
        return score;
    }
}
