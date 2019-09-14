package com.edmar.apiconsultemed.consulta.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class FiltroDataDto {
	
    @DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataAgendamento;
}
