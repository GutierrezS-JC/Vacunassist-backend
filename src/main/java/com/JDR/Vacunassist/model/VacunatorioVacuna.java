package com.JDR.Vacunassist.Model;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacunatorio_vacuna")
public class VacunatorioVacuna {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="vacunatorio_id")
	private Vacunatorio vacunatorio;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="vacuna_id")
	private Vacuna vacuna;
	
	@Column
	private Integer stock;
	
	@Column
	private Date fecha;
	
	public VacunatorioVacuna() {
		
	}
	
	public VacunatorioVacuna(Vacunatorio vacunatorio, Vacuna vacuna) {
		super();
		this.vacunatorio = vacunatorio;
		this.vacuna = vacuna;
		this.stock = 0;
		this.fecha = new Date();
	}
	
	public VacunatorioVacuna(Integer id, Vacunatorio vacunatorio, Vacuna vacuna, Integer stock, Date fecha) {
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
