package com.pruebatecnica.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.apirest.models.Response;
import com.pruebatecnica.apirest.models.DTOs.CredencialesDTO;
import com.pruebatecnica.apirest.models.DTOs.UsuarioDTO;
import com.pruebatecnica.apirest.models.services.IUsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService; 
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Response<UsuarioDTO>> obtenerUsuario(@PathVariable Long id) {
		Response<UsuarioDTO> response = usuarioService.obtenerUsuarioByID(id);
		return new ResponseEntity<Response<UsuarioDTO>>(response, response.getEstatus());
	}
	
	@GetMapping("/usuarios/page/{page}")
	public ResponseEntity<Response<Page<UsuarioDTO>>> obtenerUsuarioPage(@PathVariable Integer page) {
		Response<Page<UsuarioDTO>> response = usuarioService.listarUsuariosPage(PageRequest.of(page, 7));
		return new ResponseEntity<Response<Page<UsuarioDTO>>>(response, response.getEstatus());
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<Response<List<UsuarioDTO>>> listarUsuarioss() {
		Response<List<UsuarioDTO>> response = usuarioService.listarUsuarios();
		return new ResponseEntity<Response<List<UsuarioDTO>>>(response, response.getEstatus());
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<Response<UsuarioDTO>> crearUsuario(@Valid @RequestBody UsuarioDTO usuario, BindingResult result) {
		Response<UsuarioDTO> response = usuarioService.crearUsuario(usuario, result);
		return new ResponseEntity<Response<UsuarioDTO>>(response, response.getEstatus());
	}
	
	@GetMapping("/usuarioemail/{correo_electronico}")
	public ResponseEntity<?> obtenerUsuarioPorCorreoElectronico(@PathVariable String correo_electronico) {
		Response<UsuarioDTO> response = usuarioService.obtenerUsuarioPorCorreo(correo_electronico);
		return new ResponseEntity<Response<UsuarioDTO>>(response, response.getEstatus());
	}
	
	@PostMapping("/usuarios/login")
	public ResponseEntity<Response<UsuarioDTO>> validarCredenciales(@RequestBody CredencialesDTO credenciales) {
		Response<UsuarioDTO> response = usuarioService.validarCredenciales(credenciales.getNombre_usuario(), credenciales.getContrasenia());
		return new ResponseEntity<Response<UsuarioDTO>>(response, response.getEstatus());
	}
}
