package com.JDR.Vacunassist.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PacienteRequest {

	private Integer dni;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String email;
	private String password;
	private Boolean esRiesgo;
	private Integer zonaId;
	// Vacuna INFO
	private List<VacunasAnterioresRequest> listaVacunasAnteriores;
	
	public PacienteRequest() {
		
	}
	
	public PacienteRequest(Integer dni, String nombre, String apellido, LocalDate fechaNacimiento, String email, String password, Boolean esRiesgo,
			Integer zonaId, List<VacunasAnterioresRequest> listaVacunasAnteriores) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.password = password;
		this.esRiesgo = esRiesgo;
		this.zonaId = zonaId;
		this.listaVacunasAnteriores = listaVacunasAnteriores;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEsRiesgo() {
		return esRiesgo;
	}

	public void setEsRiesgo(Boolean esRiesgo) {
		this.esRiesgo = esRiesgo;
	}

	public Integer getZonaId() {
		return zonaId;
	}

	public void setZonaId(Integer zonaId) {
		this.zonaId = zonaId;
	}

	public List<VacunasAnterioresRequest> getListaVacunasAnteriores() {
		return listaVacunasAnteriores;
	}

	public void setListaVacunasAnteriores(List<VacunasAnterioresRequest> listaVacunasAnteriores) {
		this.listaVacunasAnteriores = listaVacunasAnteriores;
	}
	
}
