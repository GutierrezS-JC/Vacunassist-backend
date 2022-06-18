package com.JDR.Vacunassist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Paciente findByDni(Integer dni);
	
	@Query(value="SELECT coalesce(MAX(paciente.codigo),0) from paciente", nativeQuery = true)
	Integer getUltimoCodigoCreado();

}
