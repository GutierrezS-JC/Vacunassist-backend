package com.JDR.Vacunassist.Dto;

public class ValidarAdmin {
	
	private String email;
	private String password;
	private Integer codigo;
	
	public ValidarAdmin(String email, String password, Integer codigo) {
		super();
		this.email = email;
		this.password = password;
		this.codigo = codigo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
}
