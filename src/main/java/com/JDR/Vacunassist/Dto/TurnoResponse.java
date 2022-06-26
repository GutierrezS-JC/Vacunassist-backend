package com.JDR.Vacunassist.Dto;

public class TurnoResponse {

	private Integer id;
	private String nombrePaciente;
	private String apellidoPaciente;
	private Integer dni;
	private String nombreVacunatorio;
	private String nombreVacuna;
	private String fechaAplicacion;
	private String fechaAsignacion;
	private Boolean asistio;
	
	public TurnoResponse(Integer id, String nombrePaciente, String apellidoPaciente, Integer dni,
			String nombreVacunatorio, String nombreVacuna, String fechaAplicacion, String fechaAsignacion,
			Boolean asistio) {
		super();
		this.id = id;
		this.nombrePaciente = nombrePaciente;
		this.apellidoPaciente = apellidoPaciente;
		this.dni = dni;
		this.nombreVacunatorio = nombreVacunatorio;
		this.nombreVacuna = nombreVacuna;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaAsignacion = fechaAsignacion;
		this.asistio = asistio;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	
	public String getApellidoPaciente() {
		return apellidoPaciente;
	}
	
	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}
	
	public Integer getDni() {
		return dni;
	}
	
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}
	
	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
	}
	
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
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
	
}
