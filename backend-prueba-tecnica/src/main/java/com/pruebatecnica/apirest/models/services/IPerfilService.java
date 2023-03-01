package com.pruebatecnica.apirest.models.services;

import java.util.List;

import com.pruebatecnica.apirest.models.Response;

public interface IPerfilService {
	
	public Response<List<String>> obtenerPerfiles();

}
