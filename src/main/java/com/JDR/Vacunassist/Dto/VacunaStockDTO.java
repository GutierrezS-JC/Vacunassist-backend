package com.JDR.Vacunassist.Dto;

import java.util.Date;

public class VacunaStockDTO {

	private Integer vacunaId;
	private String nombreVacuna;
	private Date fechaUltimaActualizacion;
	private Integer stock;
	
	public VacunaStockDTO() {
		
	}
	
	public VacunaStockDTO(Integer vacunaId, String nombreVacuna, Date fechaUltimaActualizacion, Integer stock) {
		super();
		this.vacunaId = vacunaId;
		this.nombreVacuna = nombreVacuna;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.stock = stock;
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
	
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}
