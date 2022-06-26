package com.JDR.Vacunassist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer>{

	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getAllTurnos();
	
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id IN (1,2,3)\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getTurnosCovid();
	
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id = 4\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getTurnosGripe();
	
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id = 5\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getTurnosYellow();
}
