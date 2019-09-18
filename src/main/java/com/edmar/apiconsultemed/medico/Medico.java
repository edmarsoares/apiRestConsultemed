/**
 * 
 */
package com.edmar.apiconsultemed.medico;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edmar.apiconsultemed.pessoa.Pessoa;

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
	private String crm;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
}
