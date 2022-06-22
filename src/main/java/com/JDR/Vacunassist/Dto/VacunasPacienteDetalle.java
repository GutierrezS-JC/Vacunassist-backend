package com.JDR.Vacunassist.Dto;

public class VacunasPacienteDetalle {
	private String fechaAplicacion;
	private Integer zonaId;
	private String nombreZona;
	private Integer vacunatorioId;
	private String nombreVacunatorio;
	private Integer vacunaId;
	private String nombreVacuna;
	
	public VacunasPacienteDetalle(String fechaAplicacion, Integer zonaId, String nombreZona, Integer vacunatorioId,
			String nombreVacunatorio, Integer vacunaId, String nombreVacuna) {
		super();
		this.fechaAplicacion = fechaAplicacion;
		this.zonaId = zonaId;
		this.nombreZona = nombreZona;
		this.vacunatorioId = vacunatorioId;
		this.nombreVacunatorio = nombreVacunatorio;
		this.vacunaId = vacunaId;
		this.nombreVacuna = nombreVacuna;
	}

	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	
	public Integer getZonaId() {
		return zonaId;
	}
	
	public void setZonaId(Integer zonaId) {
		this.zonaId = zonaId;
	}
	
	public String getNombreZona() {
		return nombreZona;
	}
	
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	public Integer getVacunatorioId() {
		return vacunatorioId;
	}
	
	public void setVacunatorioId(Integer vacunatorioId) {
		this.vacunatorioId = vacunatorioId;
	}
	
	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}
	
	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
	}
	
	public Integer getVacunaId() {
		return vacunaId;
	}
	
	public void setVacunaId(Integer vacunaId) {
		this.vacunaId = vacunaId;
	}
	
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	
}
