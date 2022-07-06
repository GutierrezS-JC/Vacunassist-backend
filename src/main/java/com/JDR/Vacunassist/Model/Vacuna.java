package com.JDR.Vacunassist.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Vacuna {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	
	@NotNull
	private String nombre;
	
	@Column
	private int num_dosis;
	
	@NotNull
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@OneToMany(mappedBy = "vacuna", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VacunatorioVacuna> vacunatorios = new HashSet<>();
	
	@OneToMany(mappedBy="vacuna")
	private Set<Turno> turnos = new HashSet<>();
	
	public Vacuna() {
		
	}
	
	public Vacuna(int id, String nombre, int num_dosis, Date fechaVencimiento) {
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
