package io.github.brunnotoscano.feignclient.mapper;

import io.github.brunnotoscano.feignclient.entity.Cliente;
import io.github.brunnotoscano.feignclient.entity.ClienteDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    @Autowired
    private final ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    @Test
    public void clienteToClienteDtoMapping(){
        Cliente cliente = new Cliente();
        cliente.setClientId(1);
        cliente.setName("test");
        cliente.setBalance(100);
        cliente.setPhoneNumber(10101L);
        cliente.setCreditScore(10);

        ClienteDto dto = clienteMapper.clienteToClienteDto(cliente);

        assertEquals(dto.getId(), cliente.getClientId());
        assertEquals(dto.getNome(), cliente.getName());
        assertEquals(dto.getSaldo_cc(), cliente.getBalance());
        assertEquals(dto.getTelefone(), cliente.getPhoneNumber());
        assertEquals(dto.getScore_credito(), cliente.getCreditScore());
    }

    @Test
    public void clienteDtoToClienteMapping(){
        ClienteDto dto = new ClienteDto();
        dto.setId(1);
        dto.setNome("test");
        dto.setSaldo_cc(100);
        dto.setTelefone(10101L);
        dto.setScore_credito(10);

        Cliente cliente = clienteMapper.clienteDtoToCliente(dto);

        assertEquals(dto.getId(), cliente.getClientId());
        assertEquals(dto.getNome(), cliente.getName());
        assertEquals(dto.getSaldo_cc(), cliente.getBalance());
        assertEquals(dto.getTelefone(), cliente.getPhoneNumber());
        assertEquals(dto.getScore_credito(), cliente.getCreditScore());
    }

    @Test
    public void listClienteDtoToListClienteMapping(){
        ClienteDto dto1 = new ClienteDto();
        dto1.setId(1);
        dto1.setNome("test");
        dto1.setSaldo_cc(100);
        dto1.setTelefone(10101L);
        dto1.setScore_credito(10);

        ClienteDto dto2 = new ClienteDto();
        dto2.setId(2);
        dto2.setNome("test2");
        dto2.setSaldo_cc(200);
        dto2.setTelefone(20202L);
        dto2.setScore_credito(20);

        List<ClienteDto> dtos = new ArrayList<>();
        dtos.add(dto1);
        dtos.add(dto2);

        List<Cliente> clientes = clienteMapper.listClienteDtoToListCliente(dtos);

        for(int i = 0; i < dtos.size() ; i++){
            assertEquals(dtos.get(i).getId(), clientes.get(i).getClientId());
            assertEquals(dtos.get(i).getNome(), clientes.get(i).getName());
            assertEquals(dtos.get(i).getSaldo_cc(), clientes.get(i).getBalance());
            assertEquals(dtos.get(i).getTelefone(), clientes.get(i).getPhoneNumber());
            assertEquals(dtos.get(i).getScore_credito(), clientes.get(i).getCreditScore());
        }
    }

}