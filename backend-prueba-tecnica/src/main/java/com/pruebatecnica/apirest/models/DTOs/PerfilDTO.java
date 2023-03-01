package com.pruebatecnica.apirest.models.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class PerfilDTO {
	
	private Long id;
	
	@NotEmpty
	private String perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	

}
