package com.JDR.Vacunassist.Dto;

import java.time.LocalDate;

public class SolicitudDTO {
	private Integer id;
	private Boolean aprobado;
	private LocalDate fechaActualizacion;
	private LocalDate fechaSolicitud;
	private Integer pacienteId;
	private String pacienteNombre;
	private String pacienteApellido;
	private Integer pacienteDni;
	private Integer adminDni;
	private String adminNombre;
	private String adminApellido;
	
	public SolicitudDTO(Integer id, Boolean aprobado, LocalDate fechaActualizacion, LocalDate fechaSolicitud,
			Integer pacienteId, String pacienteNombre, String pacienteApellido, Integer pacienteDni, Integer adminDni,
			String adminNombre, String adminApellido) {
		super();
		this.id = id;
		this.aprobado = aprobado;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaSolicitud = fechaSolicitud;
		this.pacienteId = pacienteId;
		this.pacienteNombre = pacienteNombre;
		this.pacienteApellido = pacienteApellido;
		this.pacienteDni = pacienteDni;
		this.adminDni = adminDni;
		this.adminNombre = adminNombre;
		this.adminApellido = adminApellido;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getAprobado() {
		return aprobado;
	}
	
	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}
	
	public LocalDate getFechaActualizacion() {
		return fechaActualizacion;
	}
	
	public void setFechaActualizacion(LocalDate fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public Integer getPacienteId() {
		return pacienteId;
	}
	
	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}
	
	public String getPacienteNombre() {
		return pacienteNombre;
	}
	
	public void setPacienteNombre(String pacienteNombre) {
		this.pacienteNombre = pacienteNombre;
	}
	
	public String getPacienteApellido() {
		return pacienteApellido;
	}
	
	public void setPacienteApellido(String pacienteApellido) {
		this.pacienteApellido = pacienteApellido;
	}
	
	public Integer getPacienteDni() {
		return pacienteDni;
	}
	
	public void setPacienteDni(Integer pacienteDni) {
		this.pacienteDni = pacienteDni;
	}
	
	public Integer getAdminDni() {
		return adminDni;
	}
	
	public void setAdminDni(Integer adminDni) {
		this.adminDni = adminDni;
	}
	
	public String getAdminNombre() {
		return adminNombre;
	}
	
	public void setAdminNombre(String adminNombre) {
		this.adminNombre = adminNombre;
	}
	
	public String getAdminApellido() {
		return adminApellido;
	}
	
	public void setAdminApellido(String adminApellido) {
		this.adminApellido = adminApellido;
	}
	
}
