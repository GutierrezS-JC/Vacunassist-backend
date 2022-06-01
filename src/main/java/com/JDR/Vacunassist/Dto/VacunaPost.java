package com.JDR.Vacunassist.Dto;

import java.sql.Date;


public class VacunaPost {

	private int id; 
	private String nombre;
	private int num_dosis;
	private Date fechaVencimiento;
	
	public VacunaPost(int id, String nombre, int num_dosis, Date fechaVencimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.num_dosis = num_dosis;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNum_dosis() {
		return num_dosis;
	}
	
	public void setNum_dosis(int num_dosis) {
		this.num_dosis = num_dosis;
	}
	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
}
