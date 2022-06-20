package com.JDR.Vacunassist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Vacunador;

@Repository
public interface VacunadorRepository extends JpaRepository<Vacunador, Integer>{

	Vacunador findByDni(Integer dni);
	
	List<Vacunador>findByDniGreaterThanAndDniLessThan(Integer dniStart, Integer dniEnd);

//	Vacunador findByVacunadorAndPassword(Vacunador vacunadorBuscado, String password);

	Vacunador findByEmailAndPassword(String email, String password);

	Vacunador findByEmailAndPasswordAndCodigo(String email, String password, Integer codigo);

	Vacunador findByCodigo(Integer codigo);

	//Vacunadores por zona
	@Query(value="SELECT v.id, v.dni, v.email, v.nombre, v.apellido, v.fecha_nacimiento, v.rol_id, z.nombre_zona, vac.nombre as nombre_vacunatorio\r\n"
			+ "FROM vacunador v INNER JOIN zona z ON (v.zona_id = z.id) \r\n"
			+ "INNER JOIN vacunatorio vac ON (vac.zona_id = z.id)\r\n"
			+ "WHERE z.id = :zonaId",
			nativeQuery = true)
	List<Object[]> getVacunadoresEnZona(Integer zonaId);
	

}
