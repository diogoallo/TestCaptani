package com.techmahindra.cadastrocliente.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmahindra.cadastrocliente.dto.ClienteDTO;
import com.techmahindra.cadastrocliente.entity.Cliente;
import com.techmahindra.cadastrocliente.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteService clienteService;
	
	private static final String URL_BASE = "/api/clientes/";
	
	@Test
	@WithMockUser
	public void testCadastrarCliente() throws Exception {
		Cliente cliente  = obterDadosCliente();
		BDDMockito.given(this.clienteService.save(Mockito.any(Cliente.class))).willReturn(cliente);
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.nome").value("Diogo Alló"))
				.andExpect(jsonPath("$.data.cpf").value("03597703914"))
				.andExpect(jsonPath("$.data.endereco").value("Av. José Bonifácio"))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testCadastrarClienteCpfExistente() throws Exception {
		BDDMockito.given(this.clienteService.buscarPorCpf(Mockito.anyString())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Cliente encontato. Cpf existente."))
				.andExpect(jsonPath("$.data").isEmpty());
	}
	
	
	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setNome("Diogo Alló");
		clienteDto.setCpf("03597703914");
		clienteDto.setEndereco("Av. José Bonifácio");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(clienteDto);
	}
	
	private Cliente obterDadosCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Diogo Alló");
		cliente.setCpf("03597703914");
		cliente.setEndereco("Av. José Bonifácio");
		return cliente;
	}	
	
}
