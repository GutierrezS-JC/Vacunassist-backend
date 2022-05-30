package com.JDR.Vacunassist.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacunatorio_vacuna")
public class VacunatorioVacuna {
	
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="vacunadorio_id")
	private Vacunatorio vacunatorio;
	
	@ManyToOne
	@JoinColumn(name="vacuna_id")
	private Vacuna vacuna;
	
	@Column
	private Integer stock;
	
	@Column
	private Timestamp fecha;
	
	public VacunatorioVacuna() {
		
	}
	
	public VacunatorioVacuna(Integer id, Vacunatorio vacunatorio, Vacuna vacuna, Integer stock, Timestamp fecha) {
		super();
		this.id = id;
		this.vacunatorio = vacunatorio;
		this.vacuna = vacuna;
		this.stock = stock;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}

	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
}
