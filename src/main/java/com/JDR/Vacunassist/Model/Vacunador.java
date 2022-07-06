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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Vacunador {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;    
	
	@NotNull
	@Column(unique=true)
	private int dni;
	
	@NotNull
	@Column(unique=true)
	private String email;

	@NotNull
	@Column
	private String password;
	
	@Column(unique=true)
	private int codigo;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	
	@ManyToOne
	@JoinColumn(name="rol_id", nullable=false)
	private Rol rol;
	
//	@OneToMany(mappedBy = "vacunador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Set<VacunadorZona> zonas = new HashSet<>();
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "zona_id")
	private Zona zona;
	
	public Vacunador() {
	}
	
	//Para cargar el Vacunador
	public Vacunador(int id, @NotNull int dni, @NotNull String email, @NotNull String password, int codigo,
			String nombre, String apellido, Date fechaNacimiento, Rol rol) {
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.password = password;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.rol = rol;
	}

	public Vacunador(int id, @NotNull int dni, @NotNull String email, @NotNull String password, int codigo,
			String nombre, String apellido, Date fechaNacimiento, Rol rol, Zona zona) {
		super();
		this.id = id;
		this.dni = dni;
		this.email = email;
		this.password = password;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.rol = rol;
		this.zona = zona;
	}

	public Vacunador(int dni, String password, int codigo, String nombre, String apellido, String email,
			Date fechaNacimiento) {
		super();
		this.dni = dni;
		this.password = password;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
}
