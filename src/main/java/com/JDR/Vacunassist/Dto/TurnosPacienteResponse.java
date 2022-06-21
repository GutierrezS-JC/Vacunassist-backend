package com.JDR.Vacunassist.Dto;

import java.time.LocalDateTime;

public class TurnosPacienteResponse {

	private Integer turnoId;
	private Integer pacienteId;
	private String fechaAplicacion;
	private String fechaAsignacion;
	private Boolean asistio;
	private String nombreVacunatorio;
	private String calle;
	private String altura;
	private String nombreZona;
	private String nombreVacuna;
	private Integer vacunaId;
	
	public TurnosPacienteResponse(Integer turnoId, Integer pacienteId, String fechaAplicacion,
			String fechaAsignacion, Boolean asistio, String nombreVacunatorio, String calle, String altura,
			String nombreZona, String nombreVacuna, Integer vacunaId) {
		super();
		this.turnoId = turnoId;
		this.pacienteId = pacienteId;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaAsignacion = fechaAsignacion;
		this.asistio = asistio;
		this.nombreVacunatorio = nombreVacunatorio;
		this.calle = calle;
		this.altura = altura;
		this.nombreZona = nombreZona;
		this.nombreVacuna = nombreVacuna;
		this.vacunaId = vacunaId;
	}
	
	public Integer getTurnoId() {
		return turnoId;
	}
	
	public void setTurnoId(Integer turnoId) {
		this.turnoId = turnoId;
	}
	
	public Integer getPacienteId() {
		return pacienteId;
	}
	
	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}
	
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	
	public String getFechaAsignacion() {
		return fechaAsignacion;
	}
	
	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	
	public Boolean getAsistio() {
		return asistio;
	}
	
	public void setAsistio(Boolean asistio) {
		this.asistio = asistio;
	}
	
	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}
	
	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
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
	
	public String getNombreZona() {
		return nombreZona;
	}
	
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	
	public Integer getVacunaId() {
		return vacunaId;
	}
	
	public void setVacunaId(Integer vacunaId) {
		this.vacunaId = vacunaId;
	}
	
}
