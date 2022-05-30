package com.JDR.Vacunassist.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Turno {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	
	@NotNull
	@Column(name="fecha_asignacion")
	private Timestamp fechaAsignacion;
	
	@Column(name="fecha_aplicacion")
	private Timestamp fechaAplicacion;
	
	@Column
	private Boolean asistio;
	
	@ManyToOne
	@JoinColumn(name="vacuna_id", nullable=false)
	private Vacuna vacuna;
	
	@ManyToOne
	@JoinColumn(name="vacunatorio_id", nullable=false)
	private Vacunatorio vacunatorio;
	
	@ManyToOne
	@JoinColumn(name="paciente_id", nullable=false)
	private Paciente paciente;
	
	public Turno() {
		
	}

	public Turno(int id, Timestamp fechaAsignacion, Timestamp fechaAplicacion, Boolean asistio) {
		super();
		this.id = id;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaAplicacion = fechaAplicacion;
		this.asistio = asistio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Timestamp fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Timestamp getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Timestamp fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Boolean getAsistio() {
		return asistio;
	}

	public void setAsistio(Boolean asistio) {
		this.asistio = asistio;
	}
	
}
