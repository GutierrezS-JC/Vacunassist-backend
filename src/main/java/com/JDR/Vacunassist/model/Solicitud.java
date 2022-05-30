package com.JDR.Vacunassist.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Solicitud {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  
	
	@Column(name="fecha_solicitud")
	private Timestamp fechaSolicitud;
	
	@Column(name="fecha_actualizacion")
	private Timestamp fechaActualizacion;
	
	@Column
	private Boolean aprobado;
	
	@ManyToOne
	@JoinColumn(name="administrador_id", nullable=false)
	private Administrador administrador;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="paciente_id", referencedColumnName = "id", nullable=false)
	private Paciente paciente;

	public Solicitud() {
		
	}
	
	public Solicitud(int id, Timestamp fechaSolicitud, Timestamp fechaActualizacion, Boolean aprobado,
			Administrador administrador, Paciente paciente) {
		super();
		this.id = id;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaActualizacion = fechaActualizacion;
		this.aprobado = aprobado;
		this.administrador = administrador;
		this.paciente = paciente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}
