package com.JDR.Vacunassist.Dto;

import java.time.LocalDateTime;
import java.util.Date;

public class VacunasAnterioresRequest {
	private Integer vacunaId;
	private LocalDateTime fechaAplicacion;
	private Integer zonaId; //Vacunatorio
	
	public VacunasAnterioresRequest() {
		
	}
	
	public VacunasAnterioresRequest(Integer vacunaId, LocalDateTime fechaAplicacion, Integer zonaId) {
		super();
		this.vacunaId = vacunaId;
		this.fechaAplicacion = fechaAplicacion;
		this.zonaId = zonaId;
	}

	public Integer getVacunaId() {
		return vacunaId;
	}

	public void setVacunaId(Integer vacunaId) {
		this.vacunaId = vacunaId;
	}

	public LocalDateTime getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(LocalDateTime fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Integer getZonaId() {
		return zonaId;
	}

	public void setZonaId(Integer zonaId) {
		this.zonaId = zonaId;
	}
	
}
