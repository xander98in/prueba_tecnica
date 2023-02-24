package com.pruebatecnica.apirest.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class Response<T> {

	private HttpStatus estatus;
	private String mensajeUsuario;
	private String mensajeDesarrolllador;
	private List<String> errors = new ArrayList<>();
	private T Data;
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public HttpStatus getEstatus() {
		return estatus;
	}

	public void setEstatus(HttpStatus estatus) {
		this.estatus = estatus;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = mensajeUsuario;
	}

	public String getMensajeDesarrolllador() {
		return mensajeDesarrolllador;
	}

	public void setMensajeDesarrolllador(String mensajeDesarrolllador) {
		this.mensajeDesarrolllador = mensajeDesarrolllador;
	}

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}
}
