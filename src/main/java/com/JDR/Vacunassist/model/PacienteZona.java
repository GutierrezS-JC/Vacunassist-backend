package com.JDR.Vacunassist.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="paciente_zona")
public class PacienteZona {

	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="zona_id")
	private Zona zona;
	
	@Column(name="fecha_desde")
	private Timestamp fechaDesde;
	
	@Column(name="fecha_hasta")
	private Timestamp fechaHasta;

	public PacienteZona() {
		
	}
	
	public PacienteZona(Paciente paciente, Zona zona, Timestamp fechaDesde, Timestamp fechaHasta) {
		super();
		this.paciente = paciente;
		this.zona = zona;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Timestamp getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Timestamp fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Timestamp getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Timestamp fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
}
