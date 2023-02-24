package com.pruebatecnica.apirest.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pruebatecnica.apirest.models.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	@Query(
		value = """
			SELECT * 
			FROM usuarios
			WHERE usuarios.correo_electronico = ?
			""",
		nativeQuery = true
	)
	public Usuario findByEmail(String correo_electronico);
	
	@Query(
		value = """
				SELECT * 
				FROM usuarios
				WHERE usuarios.nombre_usuario = ? AND usuarios.contrasenia = ?
				""",
		nativeQuery = true
	)
	public Usuario findByUserNameAndEmail(String nombre_usuario, String contrasenia);

}
