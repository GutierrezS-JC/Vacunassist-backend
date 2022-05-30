package com.JDR.Vacunassist.Dto;

public class ZonaDTO {

	private Integer id;
	private String nombreZona;
	private VacunatorioDTO vacunatorio;
	
	public ZonaDTO() {
		
	}
	
	public ZonaDTO(Integer id, String nombreZona, VacunatorioDTO vacunatorio) {
		super();
		this.id = id;
		this.nombreZona = nombreZona;
		this.vacunatorio = vacunatorio;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombreZona() {
		return nombreZona;
	}
	
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	public VacunatorioDTO getVacunatorio() {
		return vacunatorio;
	}
	
	public void setVacunatorio(VacunatorioDTO vacunatorio) {
		this.vacunatorio = vacunatorio;
	}
	
	
}
