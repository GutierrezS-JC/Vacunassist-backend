package com.JDR.Vacunassist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Paciente;
import com.JDR.Vacunassist.Model.Zona;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer>{

	List<Zona> findByOrderByIdAsc();
}
