package io.github.brunnotoscano.feignclient.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunnotoscano.feignclient.entity.Cliente;
import io.github.brunnotoscano.feignclient.entity.ClienteDto;
import io.github.brunnotoscano.feignclient.mapper.ClienteMapper;
import io.github.brunnotoscano.feignclient.rest.controller.ClienteController;
import io.github.brunnotoscano.feignclient.service.ClienteService;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

//|**************************************************|
//| Descrição: Testes unitários do ClienteController |
//|**************************************************|

@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService;

    @Mock
    private Cliente cliente;

    @BeforeEach
    public void init(){
        cliente = new Cliente();
        cliente.setClientId(1);
        cliente.setPhoneNumber(111L);
        cliente.setName("Teste");
        cliente.setCurrentAccount(true);
        cliente.setBalance(125);
        cliente.setCreditScore(12.5F);
    }


    @Test
    public void ClienteController_GetClienteById_ReturnCliente() throws Exception{
        Integer id = 1;
        Mockito.when(clienteService.getClienteById(id)).thenReturn(cliente);

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.clientId").value(cliente.getClientId()),
                        jsonPath("$.phoneNumber").value(cliente.getPhoneNumber()),
                        jsonPath("$.name").value(cliente.getName()),
                        jsonPath("$.currentAccount").value(cliente.getCurrentAccount()),
                        jsonPath("$.balance").value(cliente.getBalance()),
                        jsonPath("$.creditScore").value(cliente.getCreditScore())

                );

    }

    @Test
    public void ClienteController_GetClienteById_Return404() throws Exception{
        Integer id = 1;
        when(clienteService.getClienteById(id)).thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente não encontrado."));

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ClienteController_Save_ReturnCliente() throws Exception{
        when(clienteService.salvar(cliente)).thenReturn(cliente);
        String body = objectMapper.writeValueAsString(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andExpectAll(
                        jsonPath("$.clientId").value(cliente.getClientId()),
                        jsonPath("$.phoneNumber").value(cliente.getPhoneNumber()),
                        jsonPath("$.name").value(cliente.getName()),
                        jsonPath("$.currentAccount").value(cliente.getCurrentAccount()),
                        jsonPath("$.balance").value(cliente.getBalance()),
                        jsonPath("$.creditScore").value(cliente.getCreditScore())

                );
    }


    @Test
    public void ClienteController_Delete_Return204() throws Exception {
        Integer id = 1;

        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void ClienteController_Delete_Return404() throws Exception{

        Integer id = 1;

        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."))
                .when(clienteService).deletar(eq(id));

        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ClienteController_Update_Return200() throws Exception{
        Integer id = 1;
        String body = objectMapper.writeValueAsString(cliente);


        mockMvc.perform(put("/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }


    @Test
    public void ClienteController_Find_ReturnCliente() throws Exception {
        List<Cliente> clientesSimulados = new ArrayList<>();
        clientesSimulados.add(cliente);
        when(clienteService.buscar(argThat(clienteBuscado -> clienteBuscado.getName().equals(cliente.getName())))).thenReturn(clientesSimulados);


        // Faz uma requisição GET para o endpoint "/api/clientes/buscar" com os parâmetros adequados
        mockMvc.perform(get("/clientes/buscar")
                        .param("name", cliente.getName()))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].clientId").value(cliente.getClientId()),
                        jsonPath("$[0].name").value(cliente.getName()),
                        jsonPath("$[0].currentAccount").value(cliente.getCurrentAccount())
                );

    }

    @Test
    public void ClienteController_Find_ReturnEmpty() throws Exception {
        List<Cliente> clientesSimulados = new ArrayList<>();
        when(clienteService.buscar(argThat(clienteBuscado -> clienteBuscado.getName().equals(cliente.getName())))).thenReturn(clientesSimulados);


        // Faz uma requisição GET para o endpoint "/api/clientes/buscar" com os parâmetros adequados
        mockMvc.perform(get("/clientes/buscar")
                        .param("name", cliente.getName()))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].clientId").doesNotExist(),
                        jsonPath("$[0].name").doesNotExist(),
                        jsonPath("$[0].currentAccount").doesNotExist(),
                        jsonPath("$[0].balance").doesNotExist(),
                        jsonPath("$[0].phoneNumber").doesNotExist(),
                        jsonPath("$[0].creditScore").doesNotExist()
                );
    }

    @Test
    public void ClienteController_CalculaScore_ReturnScore() throws Exception {
        Integer id = 1;
        when(clienteService.calculaScore(id)).thenReturn(cliente.getCreditScore());

        MvcResult result = mockMvc.perform(patch("/clientes/score/{id}", id))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(Float.toString(cliente.getCreditScore()), result.getResponse().getContentAsString());
    }

}
