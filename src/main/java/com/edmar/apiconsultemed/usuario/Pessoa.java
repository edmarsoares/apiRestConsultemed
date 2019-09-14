package com.edmar.apiconsultemed.usuario;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
@Data
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	@Length(min=3, max=20, message="O nome não pode ser vazio")
	private String nome;
	
	@Column
	private String telefone;
	
	@Column
	@CPF(message = "Entre com um CPF valido!")
	private String cpf;
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	@NotNull
	@Valid
	private Usuario usuario;
		
}
