package com.edmar.apiconsultemed.usuario;

public enum TipoUsuario {
	PACIENTE("paciente"), MEDICO("médico"), fUNCIONARIO("funcionario");

	private String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public static TipoUsuario[] getStatus() {
		return TipoUsuario.values();
	}

	public String getDescricao() {
		return this.descricao;
	}

}
