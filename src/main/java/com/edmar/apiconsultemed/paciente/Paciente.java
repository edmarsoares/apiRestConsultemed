package com.edmar.apiconsultemed.paciente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

import com.edmar.apiconsultemed.usuario.Pessoa;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ricardo belo
 *
 */

@Entity
@EqualsAndHashCode
@Data
public class Paciente implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	@Length(min=3, max=20, message="O prontuario não pode ser vazio")
	private String prontuario;
	
	@Embedded
	@Valid
	private Pessoa pessoa;

}

