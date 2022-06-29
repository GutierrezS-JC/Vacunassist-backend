package com.JDR.Vacunassist.Dto;

public class CargarTurno {
	private Integer solicitudId;
	private Integer pacienteId;
	private Integer adminId;
	private String fechaTurno;
	private String horaTurno;
	
	public CargarTurno(Integer solicitudId, Integer pacienteId, Integer adminId, String fechaTurno, String horaTurno) {
		super();
		this.solicitudId = solicitudId;
		this.pacienteId = pacienteId;
		this.adminId = adminId;
		this.fechaTurno = fechaTurno;
		this.horaTurno = horaTurno;
	}
		
	public Integer getSolicitudId() {
		return solicitudId;
	}

	public void setSolicitudId(Integer solicitudId) {
		this.solicitudId = solicitudId;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}
	
	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}
	
	public Integer getAdminId() {
		return adminId;
	}
	
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public String getFechaTurno() {
		return fechaTurno;
	}
	
	public void setFechaTurno(String fechaTurno) {
		this.fechaTurno = fechaTurno;
	}
	
	public String getHoraTurno() {
		return horaTurno;
	}
	
	public void setHoraTurno(String horaTurno) {
		this.horaTurno = horaTurno;
	}
	
}
