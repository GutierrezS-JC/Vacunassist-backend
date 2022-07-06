package com.JDR.Vacunassist.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
public class Rol {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	
	@NotNull
	@Column(unique=true)
	private String nombreRol;
	
	@OneToMany(mappedBy="rol")
	private Set<Administrador> administradores = new HashSet<>();
	
	@OneToMany(mappedBy="rol")
	private Set<Vacunador> vacunadores = new HashSet<>();
	
	@OneToMany(mappedBy="rol")
	private Set<Paciente> pacientes = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
	  name = "roles_permisos", 
	  joinColumns = @JoinColumn(name = "rol_id"), 
	  inverseJoinColumns = @JoinColumn(name = "permiso_id"))
	Set<Permiso> permisos = new HashSet<>();
	
	public Rol() {
		
	}

	public Rol(int id, String nombreRol, Set<Permiso> permisos) {
		super();
		this.id = id;
		this.nombreRol = nombreRol;
		this.permisos = permisos;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombreRol() {
		return nombreRol;
	}


	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@JsonIgnore
	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
	
}
