package com.JDR.Vacunassist.Model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacunador_zona")
public class VacunadorZona {

	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="vacunador_id")
	private Vacunador vacunador;
	
	@ManyToOne
	@JoinColumn(name="zona_id")
	private Zona zona;
	
	@Column(name="fecha_desde")
	private Date fechaDesde;
	
	@Column(name="fecha_hasta")
	private Date fechaHasta;

	public VacunadorZona() {
		
	}
	
	//Usado para cargar el vacunador
	public VacunadorZona(Vacunador vacunador, Zona zona) {
		super();
		this.vacunador = vacunador;
		this.zona = zona;
		this.fechaDesde = new Date();
	}

	public VacunadorZona(Integer id, Vacunador vacunador, Zona zona, Timestamp fechaDesde, Timestamp fechaHasta) {
		super();
		this.id = id;
		this.vacunador = vacunador;
		this.zona = zona;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vacunador getVacunador() {
		return vacunador;
	}

	public void setVacunador(Vacunador vacunador) {
		this.vacunador = vacunador;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
}
