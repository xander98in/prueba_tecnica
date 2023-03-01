package com.pruebatecnica.apirest.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pruebatecnica.apirest.models.entity.Perfil;

public interface IPerfilRepository extends JpaRepository<Perfil, Long> {
	
	
	@Query(
			value = """
				SELECT perfil 
				FROM perfiles
				""",
			nativeQuery = true
		)
	public List<String> findAllNamePerfil();

}
