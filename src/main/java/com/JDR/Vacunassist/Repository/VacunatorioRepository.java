package com.JDR.Vacunassist.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Vacunatorio;

@Repository
public interface VacunatorioRepository extends JpaRepository<Vacunatorio, Integer>{

	Optional<Vacunatorio> findByNombre(String nombre);

	@Query(value="SELECT * FROM vacunatorio where zona_id = :zonaId", nativeQuery = true)
	Vacunatorio getVacunatorioByZona(Integer zonaId);

}
