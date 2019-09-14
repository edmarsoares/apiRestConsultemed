package com.edmar.apiconsultemed.consulta;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edmar.apiconsultemed.agendamento.Agendamento;
import com.edmar.apiconsultemed.medico.Medico;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode
public class Consulta implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String descricao;
	
	@OneToOne()
	@JoinColumn(name="id_medico" )
	private Medico medico;
	
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="id_agendamento")
	private Agendamento agendamento;
	
}
