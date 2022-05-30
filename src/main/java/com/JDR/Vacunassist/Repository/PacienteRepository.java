package com.JDR.Vacunassist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JDR.Vacunassist.Model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
