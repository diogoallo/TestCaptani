package com.techmahindra.cadastrocliente.service;

import java.util.Optional;

import com.techmahindra.cadastrocliente.entity.Cliente;

public interface ClienteService {
	
	/**
	 * Retorna uma cliente dado um CPF.
	 * 
	 * @param cpf
	 * @return Optional<Clienet>
	 */
	Optional<Cliente> buscarPorCpf(String cpf);
	
	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Cliente save(Cliente cliente);

}
