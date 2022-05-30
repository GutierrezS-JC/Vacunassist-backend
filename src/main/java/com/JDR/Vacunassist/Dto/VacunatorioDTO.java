package com.JDR.Vacunassist.Dto;

//Esta clase es para el DTO de ZONA
public class VacunatorioDTO {
	
	private Integer id;
	private String nombreVacunatorio;
	
	public VacunatorioDTO() {
		
	}
	
	public VacunatorioDTO(Integer id, String nombreVacunatorio) {
		super();
		this.id = id;
		this.nombreVacunatorio = nombreVacunatorio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}

	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
	}
	
}
