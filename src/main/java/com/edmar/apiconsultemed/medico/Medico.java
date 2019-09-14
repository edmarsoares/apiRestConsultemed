/**
 * 
 */
package com.edmar.apiconsultemed.medico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import com.edmar.apiconsultemed.usuario.Pessoa;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author edmar soares
 *
 */

@Entity
@Table()
@Data
@EqualsAndHashCode
public class Medico implements Serializable{ 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CRM")
	@NotBlank(message = "O crm pode ser vazio")
	private String crm;
	
	@Embedded
	@Valid
	private Pessoa pessoa;

}
