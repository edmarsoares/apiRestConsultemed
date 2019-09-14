package com.edmar.apiconsultemed.agendamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.edmar.apiconsultemed.paciente.Paciente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode
public class Agendamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "data_agendamento")
    @DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataAgendamento;

	@Column(name ="hora_agendamento")
	private LocalTime horaAgendamento;

	@OneToOne()
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Enumerated(EnumType.STRING)
	private StatusAgendamento status;

}
