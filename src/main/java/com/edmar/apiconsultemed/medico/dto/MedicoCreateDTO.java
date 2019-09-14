package com.edmar.apiconsultemed.medico.dto;

import com.edmar.apiconsultemed.medico.Medico;

import lombok.Data;

/**
 * Dto responsável pela criação de um um objeto médico
 * @return
 */
@Data
public class MedicoCreateDTO {
		
	private String crm;
	
	private PessoaCreateDTO pessoa;
	
	public  Medico converteToMedico() {
		Medico medico = new Medico();
		
		medico.setCrm(this.crm);
		medico.setPessoa(pessoa.convertToPessoa());
		
		return medico;
	};
}
