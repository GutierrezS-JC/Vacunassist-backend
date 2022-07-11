package com.JDR.Vacunassist.Dto;

public class GenerarListadoRequest {
	private Integer pacienteDni;
	private Integer vacunaId;
	private Integer vacunatorioId;
	private String fechaInicio;
	private String fechaFin;
	
	public GenerarListadoRequest(Integer pacienteDni, Integer vacunaId, Integer vacunatorioId, String fechaInicio,
			String fechaFin) {
		super();
		this.pacienteDni = pacienteDni;
		this.vacunaId = vacunaId;
		this.vacunatorioId = vacunatorioId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Integer getPacienteDni() {
		return pacienteDni;
	}
	
	public void setPacienteDni(Integer pacienteDni) {
		this.pacienteDni = pacienteDni;
	}
	
	public Integer getVacunaId() {
		return vacunaId;
	}
	
	public void setVacunaId(Integer vacunaId) {
		this.vacunaId = vacunaId;
	}
	
	public Integer getVacunatorioId() {
		return vacunatorioId;
	}
	
	public void setVacunatorioId(Integer vacunatorioId) {
		this.vacunatorioId = vacunatorioId;
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
