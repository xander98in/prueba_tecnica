package com.pruebatecnica.apirest.models.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
	
	private Long id;
	
	@NotEmpty
	@Size(min = 5, max = 12)
	@Column(nullable = false, unique = true)
	private String nombre_usuario;
	
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String correo_electronico;
	
	@NotEmpty
	@Column(nullable = false)
	private String contrasenia;
	
	@NotEmpty
	@Column(nullable = false)
	private String perfil;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
}
