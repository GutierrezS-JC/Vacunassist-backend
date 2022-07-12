package com.JDR.Vacunassist.Dto;

public class ReporteTotalResponse {
	private String nombreVacunatorio;
	private Integer cantidadTotal;
	private Integer cantidadPendientes;
	private Integer cantidadTurnosAsistidos;
	private Integer cantidadTurnosNoAsistidos;
	
	public ReporteTotalResponse(String nombreVacunatorio, Integer cantidadTotal, Integer cantidadPendientes,
			Integer cantidadTurnosAsistidos, Integer cantidadTurnosNoAsistidos) {
		super();
		this.nombreVacunatorio = nombreVacunatorio;
		this.cantidadTotal = cantidadTotal;
		this.cantidadPendientes = cantidadPendientes;
		this.cantidadTurnosAsistidos = cantidadTurnosAsistidos;
		this.cantidadTurnosNoAsistidos = cantidadTurnosNoAsistidos;
	}
	
	public String getNombreVacunatorio() {
		return nombreVacunatorio;
	}
	
	public void setNombreVacunatorio(String nombreVacunatorio) {
		this.nombreVacunatorio = nombreVacunatorio;
	}
	
	public Integer getCantidadTotal() {
		return cantidadTotal;
	}
	
	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	
	public Integer getCantidadPendientes() {
		return cantidadPendientes;
	}
	
	public void setCantidadPendientes(Integer cantidadPendientes) {
		this.cantidadPendientes = cantidadPendientes;
	}
	
	public Integer getCantidadTurnosAsistidos() {
		return cantidadTurnosAsistidos;
	}
	
	public void setCantidadTurnosAsistidos(Integer cantidadTurnosAsistidos) {
		this.cantidadTurnosAsistidos = cantidadTurnosAsistidos;
	}
	
	public Integer getCantidadTurnosNoAsistidos() {
		return cantidadTurnosNoAsistidos;
	}
	
	public void setCantidadTurnosNoAsistidos(Integer cantidadTurnosNoAsistidos) {
		this.cantidadTurnosNoAsistidos = cantidadTurnosNoAsistidos;
	}
	
	
}
