package com.JDR.Vacunassist.Dto;

import java.sql.Date;

public class VacunadorListNative {

	private Integer id;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String mail;
	private String Zona;
	private String vacunatorioNombre;
	
	
	public VacunadorListNative() {
		super();
	}

	public VacunadorListNative(Integer id, Integer dni, String apellido, String mail, String nombre, String zona,
			String vacunatorioNombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.dni = dni;
		Zona = zona;
		this.vacunatorioNombre = vacunatorioNombre;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Integer getDni() {
		return dni;
	}
	
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public String getZona() {
		return Zona;
	}
	
	public void setZona(String zona) {
		Zona = zona;
	}
	
	public String getVacunatorioNombre() {
		return vacunatorioNombre;
	}
	
	public void setVacunatorioNombre(String vacunatorioNombre) {
		this.vacunatorioNombre = vacunatorioNombre;
	}
	
}
