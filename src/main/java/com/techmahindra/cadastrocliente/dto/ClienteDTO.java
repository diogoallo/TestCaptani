package com.techmahindra.cadastrocliente.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
	
	@NotNull(message = "Nome do cliente não pode ser vazio.")
	private String nome;
	
	@NotNull(message = "Cpf do cliente não pode ser vazio.")
	@CPF(message = "CPF inválido.")
	private String cpf;
	
	@NotNull(message = "Endereço do cliente não pode ser vazio.")
	private String endereco;
	
}
