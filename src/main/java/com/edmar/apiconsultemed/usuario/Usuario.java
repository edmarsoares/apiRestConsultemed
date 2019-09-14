
package com.edmar.apiconsultemed.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author edmar soares de lima
 *
 */
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column()
//	@Length(min=10, max=20, message="O nome não pode ser vazio")
	private String nome;
	
	@Length(min=8, max=50, message="O Email de acesso não pode ser vazio")
	private String login;
	
	@Column()
	private String senha;
	
//	@Enumerated(EnumType.STRING)
//	private TipoUsuario tipoUsuario;
	
}
