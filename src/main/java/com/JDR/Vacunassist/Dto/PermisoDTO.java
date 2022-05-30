package com.JDR.Vacunassist.Dto;

public class PermisoDTO {
	
	private Integer id;
	private String nombrePermiso;
	
	public PermisoDTO() {
		
	}
	
	public PermisoDTO(Integer id, String nombrePermiso) {
		super();
		this.id = id;
		this.nombrePermiso = nombrePermiso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombrePermiso() {
		return nombrePermiso;
	}

	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	
}
