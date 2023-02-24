package com.pruebatecnica.apirest.models.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.pruebatecnica.apirest.models.Response;
import com.pruebatecnica.apirest.models.DTOs.UsuarioDTO;
import com.pruebatecnica.apirest.models.entity.Usuario;
import com.pruebatecnica.apirest.models.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	@Transactional(readOnly = true)
	public Response<List<UsuarioDTO>> listarUsuarios() {
		
		Response<List<UsuarioDTO>> response = new Response<>();
	
		List<Usuario> usuariosEntity = new ArrayList<>();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		
		try {
			usuariosEntity = usuarioRepository.findAll();
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensajeUsuario("Se presento un error al consultar los usuarios");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		if( !usuariosEntity.isEmpty() ) {
			UsuarioDTO usuarioDTO = null;
			for(Usuario usuarioEntity : usuariosEntity ) {
				usuarioDTO = modelMapper.map(usuarioEntity, UsuarioDTO.class);
				usuariosDTO.add(usuarioDTO);
			}
		}
		else {
			response.setEstatus(HttpStatus.OK);
			response.setMensajeUsuario("No hay usuarios registrados");
			response.setMensajeDesarrolllador("No hay data registrada");
			response.setData(usuariosDTO);
			return response;
		}
		
		response.setEstatus(HttpStatus.OK);
		response.setMensajeUsuario("Los usuarios se consultaron con exito");
		response.setMensajeDesarrolllador("Se obtuvo la coleccion de los usuarios");
		response.setData(usuariosDTO);		
		
		return response;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response<Page<UsuarioDTO>> listarUsuariosPage(Pageable pageable) {
		
		Response<Page<UsuarioDTO>> response = new Response<>();
		
		Page<Usuario> usuariosEntity = null;
		Page<UsuarioDTO> usuariosDTO = null;
		
		try {
			usuariosEntity = usuarioRepository.findAll(pageable);
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensajeUsuario("Se presento un error al consultar los usuarios");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		
		usuariosDTO = usuariosEntity.map(entity -> {
			return modelMapper.map(entity, UsuarioDTO.class);
		});
		
		if(usuariosDTO.isEmpty()) {
			response.setEstatus(HttpStatus.OK);
			response.setMensajeUsuario("No hay usuarios registrados");
			response.setMensajeDesarrolllador("No hay data registrada");
			response.setData(usuariosDTO);
			return response;
		}
		
		response.setEstatus(HttpStatus.OK);
		response.setMensajeUsuario("Los usuarios se consultaron con exito");
		response.setMensajeDesarrolllador("Se obtuvo la coleccion de los usuarios");
		response.setData(usuariosDTO);		
		
		return response;
	}

	@Override
	@Transactional
	public Response<UsuarioDTO> crearUsuario(UsuarioDTO usuario, BindingResult result) {
		Response<UsuarioDTO> response = new Response<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {
						return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
					})
					.collect(Collectors.toList());
			response.setEstatus(HttpStatus.BAD_REQUEST);
			response.setMensajeUsuario("Algun o algunos campos no son validos");
			response.setMensajeDesarrolllador("Campos invalidos, violacion de validación");
			response.setErrors(errors);
			return response;
		}
		
		Usuario usuarioGuardar = null;
		try {
			usuarioGuardar = modelMapper.map(usuario, Usuario.class);
			usuarioGuardar = usuarioRepository.save(usuarioGuardar);
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensajeUsuario("Se presento un error al crear el nuevo usuario");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		
		UsuarioDTO usuarioRetornar = modelMapper.map(usuarioGuardar, UsuarioDTO.class);
		
		response.setEstatus(HttpStatus.CREATED);
		response.setMensajeUsuario("El usuario se ha creado con exito");
		response.setMensajeDesarrolllador("La entidad ha sido creada con exito en la base de datos");
		response.setData(usuarioRetornar);

		return response;
	}

	@Override
	@Transactional
	public Response<UsuarioDTO> obtenerUsuarioPorCorreo(String correo_electronico) {
		Response<UsuarioDTO> response = new Response<>();
		
		Usuario usuarioConsultar = null;

		try {
			usuarioConsultar = usuarioRepository.findByEmail(correo_electronico);
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensajeUsuario("Se presento un error al realizar la consulta");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		if(usuarioConsultar == null) {
			response.setEstatus(HttpStatus.NOT_FOUND);
			response.setMensajeUsuario("El usuario con correo: ".concat(correo_electronico.concat(" no se encuentra registrado")));
			response.setMensajeDesarrolllador("El dato no se encuentra registrado");
			return response;
		}
		UsuarioDTO usuarioRetornar = modelMapper.map(usuarioConsultar, UsuarioDTO.class);
		
		response.setEstatus(HttpStatus.OK);
		response.setMensajeUsuario("El usuario con correo: ".concat(correo_electronico.concat(" se encontro con éxito")));
		response.setMensajeDesarrolllador("La entidad se encontro con exito en la base de datos");
		response.setData(usuarioRetornar);
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<UsuarioDTO> obtenerUsuarioByID(Long id) {
		
		Response<UsuarioDTO> response = new Response<>();
		
		Usuario usuarioConsultar = null;
		try {
			usuarioConsultar = usuarioRepository.findById(id).orElse(null);
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.NOT_FOUND);
			response.setMensajeUsuario("Error al realizar la consulta en la base de datos");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		
		if(usuarioConsultar == null) {
			response.setEstatus(HttpStatus.NOT_FOUND);
			response.setMensajeUsuario("El usuario con ID: ".concat(id.toString().concat(" no se encuentra registrado")));
			response.setMensajeDesarrolllador("El dato no se encuentra registrado");
			return response;
		}
		
		UsuarioDTO usuarioRetornar = modelMapper.map(usuarioConsultar, UsuarioDTO.class);
		
		response.setEstatus(HttpStatus.OK);
		response.setMensajeUsuario("El usuario con ID: ".concat(id.toString().concat(" se encontro con éxito")));
		response.setMensajeDesarrolllador("El dato se encuentra registrado");
		response.setData(usuarioRetornar);
		
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<UsuarioDTO> validarCredenciales(String nombre_usuario, String contrasenia) {

		Response<UsuarioDTO> response = new Response<>();
		
		Usuario usuarioValidado = null;
		try {
			usuarioValidado = usuarioRepository.findByUserNameAndEmail(nombre_usuario, contrasenia);
		}
		catch( DataAccessException e ) {
			response.setEstatus(HttpStatus.NOT_FOUND);
			response.setMensajeUsuario("Error al realizar la consulta en la base de datos");
			response.setMensajeDesarrolllador(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.setErrors(new ArrayList<String>(Arrays.asList(e.getMessage())));
			return response;
		}
		
		if(usuarioValidado == null) {
			response.setEstatus(HttpStatus.NOT_FOUND);
			response.setMensajeUsuario("Las credenciales del usuario no son validas");
			response.setMensajeDesarrolllador("Error en las credenciales del usuario");
			return response;
		}
		
		UsuarioDTO usuarioLogeado = modelMapper.map(usuarioValidado, UsuarioDTO.class);
		
		response.setEstatus(HttpStatus.OK);
		response.setMensajeUsuario("Las credenciales del usuario son validas");
		response.setMensajeDesarrolllador("Credenciales validad de la entidad");
		response.setData(usuarioLogeado);
		
		return response;
	}
}
