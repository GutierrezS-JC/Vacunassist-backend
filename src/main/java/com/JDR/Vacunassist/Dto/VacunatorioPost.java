package com.JDR.Vacunassist.Dto;

public class VacunatorioPost {

	private Integer id;
	private String nombre;
	private String calle;
	private String altura;
	private Integer zonaId;
	
	public VacunatorioPost(Integer id, String nombre, String calle, String altura, Integer zonaId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.zonaId = zonaId;
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

	public Integer getZonaId() {
		return zonaId;
	}

	public void setZonaId(Integer zonaId) {
		this.zonaId = zonaId;
	}
	
}
