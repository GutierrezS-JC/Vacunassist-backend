package com.JDR.Vacunassist.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Paciente findByDni(Integer dni);
	
	@Query(value="SELECT coalesce(MAX(paciente.codigo),1000) from paciente", nativeQuery = true)
	Integer getUltimoCodigoCreado();
	
	@Query(value="SELECT MAX(time(fecha_aplicacion)) as ultimo_turno FROM turno where date(fecha_aplicacion) = :fecha and vacunatorio_id = :vacunatorioId",
			nativeQuery = true)
	Object getUltimoTurnoEnFecha(String fecha, Integer vacunatorioId);

	//Pacientes por zona
	@Query(value="SELECT p.id, p.dni, p.email, p.nombre, p.apellido, p.es_de_riesgo, p.fecha_nacimiento, p.rol_id, z.nombre_zona, vac.nombre as nombre_vacunatorio\r\n"
			+ "FROM paciente p INNER JOIN zona z ON (p.zona_id = z.id)\r\n"
			+ "INNER JOIN vacunatorio vac ON (vac.zona_id = z.id)\r\n"
			+ "WHERE z.id = :zonaId",
			nativeQuery = true)
	List<Object[]> getPacientesEnZona(Integer zonaId);
}
