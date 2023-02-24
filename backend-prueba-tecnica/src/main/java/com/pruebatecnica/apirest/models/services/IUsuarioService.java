package com.pruebatecnica.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import com.pruebatecnica.apirest.models.Response;
import com.pruebatecnica.apirest.models.DTOs.UsuarioDTO;

public interface IUsuarioService {
	
	public Response<List<UsuarioDTO>> listarUsuarios();
	
	public Response<Page<UsuarioDTO>> listarUsuariosPage(Pageable pageable);

	public Response<UsuarioDTO> crearUsuario(UsuarioDTO usuario, BindingResult result);
	
	public Response<UsuarioDTO> obtenerUsuarioByID(Long id);
	
	public Response<UsuarioDTO> obtenerUsuarioPorCorreo(String correo_electronico);
	
	public Response<UsuarioDTO> validarCredenciales(String nombre_usuario, String contrasenia);
	
}
