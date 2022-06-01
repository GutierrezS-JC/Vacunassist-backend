package com.JDR.Vacunassist.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Vacunatorio {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;    
	
	@NotNull
	@Column(unique=true)
	private String nombre;
	
	@Column
	private String calle;
	
	@Column
	private String altura;
	
	@Column
	private int piso;

	@Column
	private int dpto;
	
	@OneToMany(mappedBy="vacunatorio")
	private Set<Turno> turnos = new HashSet<>();
	
	@OneToMany(mappedBy = "vacunatorio")
	private Set<VacunatorioVacuna> vacunas = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zona_id", referencedColumnName = "id")
    private Zona zona;
	
	public Vacunatorio() {
		
	}

	public Vacunatorio(int id, String nombre, String calle, String altura, int piso, int dpto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.dpto = dpto;
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

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getDpto() {
		return dpto;
	}

	public void setDpto(int dpto) {
		this.dpto = dpto;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Set<VacunatorioVacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(Set<VacunatorioVacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public Set<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(Set<Turno> turnos) {
		this.turnos = turnos;
	}

}
