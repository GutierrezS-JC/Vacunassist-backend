package com.JDR.Vacunassist.Dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VacunadorDTO {
	
	private Integer id;
	private Integer dni;
	private String email;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private RolDTO rol;
	private ZonaDTO zona;
	
	public VacunadorDTO() {
		
	}
	
	public VacunadorDTO(Integer id, Integer dni, String email, String nombre, String apellido, Date fechaNacimiento,
			RolDTO rol, ZonaDTO zona) {
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.rol = rol;
		this.zona = zona;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public ZonaDTO getZona() {
		return zona;
	}

	public void setZona(ZonaDTO zona) {
		this.zona = zona;
	}

}
