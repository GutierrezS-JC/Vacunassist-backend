package com.JDR.Vacunassist.Dto;

public class MetricasResponse {
	private Integer totalTurnos;
	private Integer totalCovid;
	private Integer totalGripe;
	private Integer totalYellow;
	
	public MetricasResponse(Integer totalTurnos, Integer totalCovid, Integer totalGripe, Integer totalYellow) {
		super();
		this.totalTurnos = totalTurnos;
		this.totalCovid = totalCovid;
		this.totalGripe = totalGripe;
		this.totalYellow = totalYellow;
	}
	
	public Integer getTotalTurnos() {
		return totalTurnos;
	}
	
	public void setTotalTurnos(Integer totalTurnos) {
		this.totalTurnos = totalTurnos;
	}
	
	public Integer getTotalCovid() {
		return totalCovid;
	}
	
	public void setTotalUno(Integer totalCovid) {
		this.totalCovid = totalCovid;
	}
	
	public Integer getTotalGripe() {
		return totalGripe;
	}
	
	public void setTotalDos(Integer totalGripe) {
		this.totalGripe = totalGripe;
	}
	
	public Integer getTotalYellow() {
		return totalYellow;
	}
	
	public void setTotalTres(Integer totalYellow) {
		this.totalYellow = totalYellow;
	}
	
}
