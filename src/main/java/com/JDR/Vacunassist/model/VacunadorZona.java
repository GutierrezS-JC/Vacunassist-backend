package com.JDR.Vacunassist.model;

import java.sql.Timestamp;

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
	private Timestamp fechaDesde;
	
	@Column(name="fecha_hasta")
	private Timestamp fechaHasta;

	public VacunadorZona() {
		
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
