package com.edmar.apiconsultemed.usuario.infraestructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.usuario.Usuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Long> {
	
	@Query("select case when (count(u.id) > 0) then true else false end from Usuario u where u.login= :login " + 
			" and u.login != :loginAntigo")
	boolean verificarExistenciaLogin( @Param("login") final String login,@Param("loginAntigo") final String loginAntigo);
}
