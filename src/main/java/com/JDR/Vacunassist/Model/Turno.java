package com.JDR.Vacunassist.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
	private LocalDateTime fechaAsignacion;
	
	@Column(name="fecha_aplicacion")
	private LocalDateTime fechaAplicacion;
	
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

	public Turno(int id, LocalDateTime fechaAsignacion, LocalDateTime fechaAplicacion, Boolean asistio) {
		super();
		this.id = id;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaAplicacion = fechaAplicacion;
		this.asistio = asistio;
	}
	
	

	public Turno(int id, @NotNull LocalDateTime fechaAsignacion, LocalDateTime fechaAplicacion, Boolean asistio, Vacuna vacuna,
			Vacunatorio vacunatorio, Paciente paciente) {
		super();
		this.id = id;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaAplicacion = fechaAplicacion;
		this.asistio = asistio;
		this.vacuna = vacuna;
		this.vacunatorio = vacunatorio;
		this.paciente = paciente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public LocalDateTime getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(LocalDateTime fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Boolean getAsistio() {
		return asistio;
	}

	public void setAsistio(Boolean asistio) {
		this.asistio = asistio;
	}

	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}

	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
