package io.github.brunnotoscano.feignclient.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunnotoscano.feignclient.client.response.Cliente;
import io.github.brunnotoscano.feignclient.rest.controller.ClienteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
    private ClienteClient clienteClient;

    @Mock
    private Cliente cliente;

    @BeforeEach
    public void init(){
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setTelefone(111L);
        cliente.setNome("Teste");
        cliente.setCorrentista(true);
        cliente.setSaldo_cc(125);
    }


    @Test
    public void ClienteController_GetClienteById_ReturnCliente() throws Exception{
        Integer id = 1;
        when(clienteClient.getClienteById(id)).thenReturn(cliente);

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.id").value(cliente.getId()),
                        jsonPath("$.telefone").value(cliente.getTelefone()),
                        jsonPath("$.nome").value(cliente.getNome()),
                        jsonPath("$.correntista").value(cliente.getCorrentista()),
                        jsonPath("$.saldo_cc").value(cliente.getSaldo_cc()),
                        jsonPath("$.score_credito").value(cliente.getScore_credito())

                );

    }

    @Test
    public void ClienteController_GetClienteById_Return404() throws Exception{
        Integer id = 1;
        when(clienteClient.getClienteById(id)).thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente não encontrado."));

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ClienteController_Save_ReturnCliente() throws Exception{
        when(clienteClient.salvar(cliente)).thenReturn(cliente);
        String body = objectMapper.writeValueAsString(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andExpectAll(
                        jsonPath("$.id").value(cliente.getId()),
                        jsonPath("$.telefone").value(cliente.getTelefone()),
                        jsonPath("$.nome").value(cliente.getNome()),
                        jsonPath("$.correntista").value(cliente.getCorrentista()),
                        jsonPath("$.saldo_cc").value(cliente.getSaldo_cc()),
                        jsonPath("$.score_credito").value(cliente.getScore_credito())

                );
    }

    @Test
    public void ClienteController_Save_Return400() throws Exception{
        when(clienteClient.salvar(cliente)).thenReturn(cliente);
        cliente.setNome(null);
        cliente.setTelefone(null);
        cliente.setCorrentista(null);
        String body = objectMapper.writeValueAsString(cliente);


        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isBadRequest());
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
                .when(clienteClient).deletar(eq(id));

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
    public void ClienteController_Update_Return400() throws Exception{
        Integer id = 1;
        cliente.setNome(null);
        cliente.setTelefone(null);
        cliente.setCorrentista(null);
        String body = objectMapper.writeValueAsString(cliente);

        clienteClient.atualizar(1, cliente);

        mockMvc.perform(put("/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void ClienteController_Find_ReturnCliente() throws Exception {
        List<Cliente> clientesSimulados = new ArrayList<>();
        clientesSimulados.add(cliente);
        when(clienteClient.buscar(argThat(clienteBuscado -> clienteBuscado.getNome().equals(cliente.getNome())))).thenReturn(clientesSimulados);


        // Faz uma requisição GET para o endpoint "/api/clientes/buscar" com os parâmetros adequados
        mockMvc.perform(get("/clientes/buscar")
                        .param("nome", cliente.getNome()))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].id").value(cliente.getId()),
                        jsonPath("$[0].nome").value(cliente.getNome()),
                        jsonPath("$[0].correntista").value(cliente.getCorrentista())
                );

    }

    @Test
    public void ClienteController_Find_ReturnEmpty() throws Exception {
        List<Cliente> clientesSimulados = new ArrayList<>();
        when(clienteClient.buscar(argThat(clienteBuscado -> clienteBuscado.getNome().equals(cliente.getNome())))).thenReturn(clientesSimulados);


        // Faz uma requisição GET para o endpoint "/api/clientes/buscar" com os parâmetros adequados
        mockMvc.perform(get("/clientes/buscar")
                        .param("nome", cliente.getNome()))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].id").doesNotExist(),
                        jsonPath("$[0].nome").doesNotExist(),
                        jsonPath("$[0].correntista").doesNotExist(),
                        jsonPath("$[0].saldo_cc").doesNotExist(),
                        jsonPath("$[0].telefone").doesNotExist(),
                        jsonPath("$[0].score_credito").doesNotExist()
                );

    }

}
