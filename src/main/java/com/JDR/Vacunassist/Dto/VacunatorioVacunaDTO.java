package com.JDR.Vacunassist.Dto;

import java.util.List;

public class VacunatorioVacunaDTO {

	private Integer idVacunatorio;
	private String nombreVacunatorio;
	private List<VacunaStockDTO> listaVacunas;
	
	public VacunatorioVacunaDTO() {
		
	}
	
	public VacunatorioVacunaDTO(Integer idVacunatorio, String nombreVacunatorio, List<VacunaStockDTO> listaVacunas) {
		super();
		this.idVacunatorio = idVacunatorio;
		this.nombreVacunatorio = nombreVacunatorio;
		this.listaVacunas = listaVacunas;
	}

	public Integer getIdVacunatorio() {
		return idVacunatorio;
	}

	public void setIdVacunatorio(Integer idVacunatorio) {
		this.idVacunatorio = idVacunatorio;
	}

	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}

	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
	}

	public List<VacunaStockDTO> getListaVacunas() {
		return listaVacunas;
	}

	public void setListaVacunas(List<VacunaStockDTO> listaVacunas) {
		this.listaVacunas = listaVacunas;
	} 	
	
}
