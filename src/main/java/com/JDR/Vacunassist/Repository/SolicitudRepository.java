package com.JDR.Vacunassist.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JDR.Vacunassist.Model.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	@Query(value="SELECT s.id, s.aprobado, s.fecha_actualizacion, s.fecha_solicitud, \r\n"
			+ "p.id as paciente_id, p.nombre as paciente_nombre, p.apellido as paciente_apellido, p.dni as paciente_dni,\r\n"
			+ "a.id as admin_id, a.nombre as admin_nombre, a.apellido as admin_apellido\r\n"
			+ "FROM solicitud s\r\n"
			+ "INNER JOIN paciente p ON (p.id = s.paciente_id)\r\n"
			+ "LEFT JOIN administrador a ON (a.id = s.administrador_id)",
			nativeQuery = true)
	List<Object[]> getSolicitudes();

	@Query(value="SELECT s.id, s.aprobado, s.fecha_actualizacion, s.fecha_solicitud, \r\n"
			+ "p.id as paciente_id, p.nombre as paciente_nombre, p.apellido as paciente_apellido, p.dni as paciente_dni,\r\n"
			+ "a.id as admin_id, a.nombre as admin_nombre, a.apellido as admin_apellido\r\n"
			+ "FROM solicitud s\r\n"
			+ "INNER JOIN paciente p ON (p.id = s.paciente_id)\r\n"
			+ "LEFT JOIN administrador a ON (a.id = s.administrador_id)\r\n"
			+ "WHERE s.aprobado = 1",
			nativeQuery = true)
	List<Object[]> getSolicitudesAprobadas();

	@Query(value="SELECT s.id, s.aprobado, s.fecha_actualizacion, s.fecha_solicitud, \r\n"
			+ "p.id as paciente_id, p.nombre as paciente_nombre, p.apellido as paciente_apellido, p.dni as paciente_dni,\r\n"
			+ "a.id as admin_id, a.nombre as admin_nombre, a.apellido as admin_apellido\r\n"
			+ "FROM solicitud s\r\n"
			+ "INNER JOIN paciente p ON (p.id = s.paciente_id)\r\n"
			+ "LEFT JOIN administrador a ON (a.id = s.administrador_id)\r\n"
			+ "WHERE s.aprobado = 0",
			nativeQuery = true)
	List<Object[]> getSolicitudesRechazadas();

	@Query(value="SELECT s.id, s.aprobado, s.fecha_actualizacion, s.fecha_solicitud, \r\n"
			+ "p.id as paciente_id, p.nombre as paciente_nombre, p.apellido as paciente_apellido, p.dni as paciente_dni,\r\n"
			+ "a.id as admin_id, a.nombre as admin_nombre, a.apellido as admin_apellido\r\n"
			+ "FROM solicitud s\r\n"
			+ "INNER JOIN paciente p ON (p.id = s.paciente_id)\r\n"
			+ "LEFT JOIN administrador a ON (a.id = s.administrador_id)\r\n"
			+ "WHERE s.aprobado is null",
			nativeQuery = true)
	List<Object[]> getSolicitudesPendientes();
}
