package com.techmahindra.cadastrocliente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.techmahindra.cadastrocliente.entity.Cliente;
import com.techmahindra.cadastrocliente.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

	
	@Autowired
	private ClienteService clienteService;

	private static final String CPF = "03597708755";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.clienteService.save(Mockito.any(Cliente.class))).willReturn(new Cliente());
	}

	@Test
	public void testBuscarClientePorCpf() {
		Optional<Cliente> cliente  = this.clienteService.buscarPorCpf(CPF);
		assertTrue(cliente.isPresent());
	}
	
	@Test
	public void testPersistirEmpresa() {
		Cliente cliente = this.clienteService.save(new Cliente());
		assertNotNull(cliente);
	}

}
