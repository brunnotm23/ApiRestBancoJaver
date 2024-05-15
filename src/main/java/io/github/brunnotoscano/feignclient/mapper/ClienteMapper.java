package io.github.brunnotoscano.feignclient.mapper;

import io.github.brunnotoscano.feignclient.entity.Cliente;
import io.github.brunnotoscano.feignclient.entity.ClienteDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClienteMapper {

    @Mapping(target = "id", source = "cliente.clientId")
    @Mapping(target = "nome", source = "cliente.name")
    @Mapping(target = "telefone", source = "cliente.phoneNumber")
    @Mapping(target = "correntista", source = "cliente.currentAccount")
    @Mapping(target = "score_credito", source = "cliente.creditScore")
    @Mapping(target = "saldo_cc", source = "cliente.balance")
    ClienteDto clienteToClienteDto(Cliente cliente);

    @Mapping(target = "clientId", source = "dto.id")
    @Mapping(target = "name", source = "dto.nome")
    @Mapping(target = "phoneNumber", source = "dto.telefone")
    @Mapping(target = "currentAccount", source = "dto.correntista")
    @Mapping(target = "creditScore", source = "dto.score_credito")
    @Mapping(target = "balance", source = "dto.saldo_cc")
    Cliente clienteDtoToCliente(ClienteDto dto);


    List<Cliente> listClienteDtoToListCliente(List<ClienteDto> dtos);
}
