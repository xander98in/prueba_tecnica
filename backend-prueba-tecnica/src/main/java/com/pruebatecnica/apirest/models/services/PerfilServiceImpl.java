package com.pruebatecnica.apirest.models.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.apirest.models.Response;
import com.pruebatecnica.apirest.models.repository.IPerfilRepository;

@Service
public class PerfilServiceImpl implements IPerfilService {
	
	@Autowired
	private IPerfilRepository perfilRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Response<List<String>> obtenerPerfiles() {
		Response<List<String>> response = new Response<>();
		
		List<String> listaPerfiles = new ArrayList<>();
		
		try {
			listaPerfiles = perfilRepository.findAllNamePerfil();
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensajeUsuario("Se presento un error al consultar los perfiles");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		
		response.setEstatus(HttpStatus.OK);
		response.setMensajeUsuario("Los nombres de los perfiles se obtuvieron con exito");
		response.setMensajeDesarrolllador("Se obtuvo con exito los datos solicitados");
		response.setData(listaPerfiles);
		
		return response;
	}
	
	

}
