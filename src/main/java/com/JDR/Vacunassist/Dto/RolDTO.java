package com.JDR.Vacunassist.Dto;

import java.util.List;

public class RolDTO {

	private Integer id;
	private String nombreRol; 
	private List<PermisoDTO> permisos;
	
	public RolDTO() {
		super();
	}
		
	public RolDTO(Integer id, String nombreRol, List<PermisoDTO> permisos) {
		super();
		this.id = id;
		this.nombreRol = nombreRol;
		this.permisos = permisos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<PermisoDTO> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisoDTO> permisos) {
		this.permisos = permisos;
	}
	
}
