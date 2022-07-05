package com.JDR.Vacunassist.Dto;

public class ReporteResponse {
	private Integer cantidadTotal;
	private Integer cantidadPendientes;
	private Integer cantidadTurnosAsistidos;
	private Integer cantidadTurnosNoAsistidos;
	
	public ReporteResponse(Integer cantidadTotal, Integer cantidadPendientes, Integer cantidadTurnosAsistidos,
			Integer cantidadTurnosNoAsistidos) {
		super();
		this.cantidadTotal = cantidadTotal;
		this.cantidadPendientes = cantidadPendientes;
		this.cantidadTurnosAsistidos = cantidadTurnosAsistidos;
		this.cantidadTurnosNoAsistidos = cantidadTurnosNoAsistidos;
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
