package com.edmar.apiconsultemed.agendamento;

public enum StatusAgendamento {
	CANCELADO("Cancelado"), AGENDADO("Agendado"), REAGENDADO("Reagendamento");

	private String descricao;

	private StatusAgendamento(String descricao) {
		this.descricao = descricao;
	}

	public static StatusAgendamento[] getStatus() {
		return StatusAgendamento.values();
	}

	public String getDescricao() {
		return this.descricao;
	}

}
