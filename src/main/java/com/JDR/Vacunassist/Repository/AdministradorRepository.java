package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{
	
	Administrador findByDni(Integer dni);
}
