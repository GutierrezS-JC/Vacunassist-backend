package com.JDR.Vacunassist.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class DnisValidos {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;   
	
	@NotNull
	@Column(unique=true)
	private Integer dni;
	
	@NotNull
	@Column
	private String nombre;
	
	@NotNull
	@Column
	private String apellido;

	public DnisValidos() {
		
	}
	
	public DnisValidos(Integer id, @NotNull Integer dni, @NotNull String nombre, @NotNull String apellido) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
