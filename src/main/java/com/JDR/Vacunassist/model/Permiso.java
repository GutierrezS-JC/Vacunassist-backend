package com.JDR.Vacunassist.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Permiso {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	
	@NotNull
	@Column(unique=true)
	private String nombrePermiso;
	
	@ManyToMany(mappedBy = "permisos")
	Set<Rol> roles = new HashSet<>();
	
	public Permiso() {
		
	}
	
	public Permiso(int id, String nombrePermiso, Set<Rol> roles) {
		super();
		this.id = id;
		this.nombrePermiso = nombrePermiso;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrePermiso() {
		return nombrePermiso;
	}

	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

}
