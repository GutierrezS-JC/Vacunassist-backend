package com.JDR.Vacunassist.Dto;

public class RechazarSolicitudRequest {
	private Integer adminId;
	private Integer solicitudId;

	public RechazarSolicitudRequest(Integer adminId, Integer solicitudId) {
		super();
		this.adminId = adminId;
		this.solicitudId = solicitudId;
	}
	
	public Integer getAdminId() {
		return adminId;
	}
	
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public Integer getSolicitudId() {
		return solicitudId;
	}
	
	public void setSolicitudId(Integer solicitudId) {
		this.solicitudId = solicitudId;
	}
	
}
