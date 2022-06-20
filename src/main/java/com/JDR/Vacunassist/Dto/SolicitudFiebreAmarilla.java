package com.JDR.Vacunassist.Dto;

public class SolicitudFiebreAmarilla {

	private Integer pacienteId;
	private Integer dni;
	public SolicitudFiebreAmarilla(Integer pacienteId, Integer dni) {
		super();
		this.pacienteId = pacienteId;
		this.dni = dni;
	}
	
	public Integer getPacienteId() {
		return pacienteId;
	}
	
	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}
	
	public Integer getDni() {
		return dni;
	}
	
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
}
