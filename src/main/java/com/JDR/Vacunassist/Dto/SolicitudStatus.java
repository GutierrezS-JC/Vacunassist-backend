package com.JDR.Vacunassist.Dto;

public class SolicitudStatus {
	private Boolean tieneSolicitud;
	private Boolean aceptada;
	
	public SolicitudStatus() {
		
	}
	
	public SolicitudStatus(Boolean tieneSolicitud, Boolean aceptada) {
		super();
		this.tieneSolicitud = tieneSolicitud;
		this.aceptada = aceptada;
	}

	public Boolean getTieneSolicitud() {
		return tieneSolicitud;
	}

	public void setTieneSolicitud(Boolean tieneSolicitud) {
		this.tieneSolicitud = tieneSolicitud;
	}

	public Boolean getAceptada() {
		return aceptada;
	}

	public void setAceptada(Boolean aceptada) {
		this.aceptada = aceptada;
	}
	
}
