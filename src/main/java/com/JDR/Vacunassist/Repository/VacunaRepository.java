package com.JDR.Vacunassist.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Vacuna;

@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Integer>{

//	Optional<Vacuna> findByNombre(String nombre);
	Vacuna findByNombre(String nombre);
	
	@Query(value="SELECT * FROM vacuna WHERE id IN (1,2,3) ORDER BY RAND() LIMIT 1;",nativeQuery = true)
	Vacuna getVacunaCovidRandom();
}
