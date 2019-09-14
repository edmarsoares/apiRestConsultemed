package com.edmar.apiconsultemed.funcionario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.edmar.apiconsultemed.usuario.Pessoa;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class Funcionario implements Serializable  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column
	@Length(min=3, max=20, message="O cargo não pode ser vazio")
	private String cargo;
	@Column
	@Length(min=3, max=20, message="O setor não pode ser vazio")
	private String setor;
	
	@Embedded
	private Pessoa pessoa;
}
