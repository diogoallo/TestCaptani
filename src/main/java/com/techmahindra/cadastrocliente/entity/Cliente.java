package com.techmahindra.cadastrocliente.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1710794689414387106L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "cd_cliente")
	private Integer cdCliente;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "endereco")
	private String endereco;
	
}
