package com.JDR.Vacunassist.Model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Paciente {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;    
	
	@NotNull
	@Column(unique=true)
	private int dni;
	
	@NotNull
	@Column(unique=true)
	private String email;

	@Column
	private String password;
	
	@Column
	private int codigo;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column(name="fecha_nacimiento")
	private Timestamp fechaNacimiento;
	
	@Column
	private Boolean esDeRiesgo;
	
	@OneToOne(mappedBy = "paciente")
	private Solicitud solicitud;
	
	@ManyToOne
	@JoinColumn(name="rol_id", nullable=false)
	private Rol rol;
	
	@OneToMany(mappedBy = "paciente")
	private Set<PacienteZona> zonas;
	
	@OneToMany(mappedBy="paciente")
	private Set<Turno> turnos = new HashSet<>();
	
	public Paciente() {
	}
	
	public Paciente(int dni, String password, int codigo, String nombre, String apellido, String email,
			Timestamp fechaNacimiento) {
		super();
		this.dni = dni;
		this.password = password;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
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

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
