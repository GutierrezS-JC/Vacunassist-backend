package com.JDR.Vacunassist.Dto;

public class TurnosPendientesPacienteResponse {

	private Integer turnoId;
	private String fechaAplicacion;
	private Integer pacienteDni;
	private String pacienteNombre;
	private String pacienteApellido;
	private String nombreZona;
	private String nombreVacunatorio;
	
	public TurnosPendientesPacienteResponse(Integer turnoId, String fechaAplicacion, Integer pacienteDni,
			String pacienteNombre, String pacienteApellido, String nombreZona, String nombreVacunatorio) {
		super();
		this.turnoId = turnoId;
		this.fechaAplicacion = fechaAplicacion;
		this.pacienteDni = pacienteDni;
		this.pacienteNombre = pacienteNombre;
		this.pacienteApellido = pacienteApellido;
		this.nombreZona = nombreZona;
		this.nombreVacunatorio = nombreVacunatorio;
	}
	
	public Integer getTurnoId() {
		return turnoId;
	}
	
	public void setTurnoId(Integer turnoId) {
		this.turnoId = turnoId;
	}
	
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	
	public Integer getPacienteDni() {
		return pacienteDni;
	}
	
	public void setPacienteDni(Integer pacienteDni) {
		this.pacienteDni = pacienteDni;
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
	
	public String getNombreZona() {
		return nombreZona;
	}
	
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}
	
	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
	}
}
