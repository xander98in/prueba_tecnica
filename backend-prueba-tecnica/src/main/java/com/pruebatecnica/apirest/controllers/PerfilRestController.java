package com.pruebatecnica.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.apirest.models.Response;
import com.pruebatecnica.apirest.models.services.IPerfilService;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/perfil")
public class PerfilRestController {
	
	@Autowired
	private IPerfilService perfilService;
	
	@GetMapping("")
	public ResponseEntity<Response<List<String>>> obtenerPerfiles()
	{
		Response<List<String>> response = perfilService.obtenerPerfiles();
		return new ResponseEntity<Response<List<String>>>(response, response.getEstatus());
	}

}
