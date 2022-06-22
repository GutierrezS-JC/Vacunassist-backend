package com.JDR.Vacunassist.Dto;

import java.util.ArrayList;
import java.util.List;

public class VacunasPacienteResponse {
	private Integer vacunaId;
	private String tipoVacuna;
	private String ultimaFechaVacunaAplicada;
	private List<VacunasPacienteDetalle> listaDetalles = new ArrayList<>();
	
	public VacunasPacienteResponse() {
		super();
	}

	public VacunasPacienteResponse(Integer vacunaId, String tipoVacuna, String ultimaFechaVacunaAplicada,
			List<VacunasPacienteDetalle> listaDetalles) {
		super();
		this.vacunaId = vacunaId;
		this.tipoVacuna = tipoVacuna;
		this.ultimaFechaVacunaAplicada = ultimaFechaVacunaAplicada;
		this.listaDetalles = listaDetalles;
	}
	
	public VacunasPacienteResponse(Integer vacunaId, String tipoVacuna, String ultimaFechaVacunaAplicada) {
		super();
		this.vacunaId = vacunaId;
		this.tipoVacuna = tipoVacuna;
		this.ultimaFechaVacunaAplicada = ultimaFechaVacunaAplicada;
	}

	public Integer getVacunaId() {
		return vacunaId;
	}

	public void setVacunaId(Integer vacunaId) {
		this.vacunaId = vacunaId;
	}

	public String getTipoVacuna() {
		return tipoVacuna;
	}

	public void setTipoVacuna(String tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}

	public String getUltimaFechaVacunaAplicada() {
		return ultimaFechaVacunaAplicada;
	}

	public void setUltimaFechaVacunaAplicada(String ultimaFechaVacunaAplicada) {
		this.ultimaFechaVacunaAplicada = ultimaFechaVacunaAplicada;
	}

	public List<VacunasPacienteDetalle> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<VacunasPacienteDetalle> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}
	
}
