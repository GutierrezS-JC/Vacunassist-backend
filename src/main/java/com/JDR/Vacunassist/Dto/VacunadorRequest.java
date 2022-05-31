package com.JDR.Vacunassist.Dto;

import java.sql.Date;

public class VacunadorRequest {

	private Integer id;
	private Integer dni;
	private String email;
	private String nombre;
	private String apellido;
	//5 caracteres
	private Integer clave;
	private String password;
	private Date fechaNacimiento;
	//Rol ID que sera siempre 2
	private Integer rolId;
	private Integer zonaId;
	
	public VacunadorRequest(Integer id, Integer dni, String email, String nombre, String apellido, Integer clave,
			String password, Date fechaNacimiento, Integer rolId, Integer zonaId) {
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.rolId = rolId;
		this.zonaId = zonaId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getClave() {
		return clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public Integer getZonaId() {
		return zonaId;
	}

	public void setZonaId(Integer zonaId) {
		this.zonaId = zonaId;
	}
	
}
