package com.JDR.Vacunassist.Dto;

public class VacunatorioFullDTO {
	private Integer id;
	private String nombre;
	private String calle;
	private String altura;
	private ZonaDTO zona;
	
	public VacunatorioFullDTO() {
		
	}
	
	public VacunatorioFullDTO(Integer id, String nombre, String calle, String altura, ZonaDTO zona) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.zona = zona;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public ZonaDTO getZona() {
		return zona;
	}

	public void setZona(ZonaDTO zona) {
		this.zona = zona;
	}
	
}
