package com.techmahindra.cadastrocliente.rest;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmahindra.cadastrocliente.dto.ClienteDTO;
import com.techmahindra.cadastrocliente.entity.Cliente;
import com.techmahindra.cadastrocliente.response.Response;
import com.techmahindra.cadastrocliente.service.ClienteService;

@RestController
@RequestMapping("/v1/api/clientes")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	ClienteService clienteService;

	@PostMapping()	
  	public ResponseEntity<Response<ClienteDTO>> adicionar(@Valid @RequestBody
  	  ClienteDTO clienteDto, BindingResult result) throws ParseException {
	  log.info("Adicionando cliente: {}", clienteDto.toString());
	  Response<ClienteDTO> response = new Response<ClienteDTO>();
		  
	  validarDadosExistentes(clienteDto, result); Cliente cliente =
	  this.converterDtoParaCliente(clienteDto, result);
	  
	  if(result.hasErrors()) { log.error("Erro validando cliente: {}",
	     result.getAllErrors()); result.getAllErrors().forEach(error ->
	     response.getErrors().add(error.getDefaultMessage())); return
	     ResponseEntity.badRequest().body(response); 
	  }
	  cliente = this.clienteService.save(cliente);
	  response.setData(this.converterClienteDto(cliente)); return
	  ResponseEntity.ok(response); 
	}


	/**
	 * Verifica se o cpf cadastrado já existe.
	 * 
	 * @param clienteDto
	 * @param result
	 */
	private void validarDadosExistentes(ClienteDTO clienteDto, BindingResult result) {
		Optional<Cliente> cliente = this.clienteService.buscarPorCpf(clienteDto.getCpf());
		if (cliente.isPresent()) {
			result.addError(new ObjectError("cliente", "CPF já existente.."));
		}
	}

	/**
	 * Converte um clienteDto para uma entidade Cliente.
	 * 
	 * @param lancamentoDto
	 * @param result
	 * @return Lancamento
	 * @throws ParseException
	 */
	private Cliente converterDtoParaCliente(ClienteDTO clienteDto, BindingResult result) throws ParseException {
		Cliente cliente = new Cliente();

		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setEndereco(cliente.getEndereco());

		return cliente;
	}

	/**
	 * Converte uma entidade cliente para seu respectivo DTO.
	 * 
	 * @param lancamento
	 * @return LancamentoDto
	 */
	private ClienteDTO converterClienteDto(Cliente cliente) {
		ClienteDTO clienteDto = new ClienteDTO();

		clienteDto.setNome(cliente.getNome());
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setEndereco(cliente.getEndereco());

		return clienteDto;
	}

}
