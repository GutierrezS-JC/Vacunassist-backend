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

	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE date(fecha_aplicacion) = :fechaHoy\r\n"
			+ "ORDER BY fecha_aplicacion;",
			nativeQuery = true)
	List<Object[]> getTurnosDia(String fechaHoy);
	
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE date(fecha_aplicacion) = :fechaHoy and v.id = :vacunatorioId\r\n"
			+ "ORDER BY fecha_aplicacion;",
			nativeQuery = true)
	List<Object[]> getTurnosDiaVacunatorio(String fechaHoy, Integer vacunatorioId);

	@Query(value="SELECT date(t.fecha_aplicacion) as fechaTurnoAsignado\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE date(fecha_aplicacion) > '2022-06-27'\r\n"
			+ "GROUP BY date(t.fecha_aplicacion)\r\n"
			+ "ORDER BY fecha_aplicacion;",
			nativeQuery = true)
	List<Object[]> getTurnosAsignadosDespuesDeHoy(String fechaHoy);
	
	@Query(value="SELECT time(t.fecha_aplicacion) as hora_fecha_turno\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE date(fecha_aplicacion) = :fechaHoy\r\n"
			+ "GROUP BY time(t.fecha_aplicacion)\r\n"
			+ "ORDER BY time(fecha_aplicacion)",
			nativeQuery = true)
	List<Object[]> getTurnosHorasAsignadosEnFecha(String fechaHoy);
	
	// METRICAS
	
	@Query(value="SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_pendientes\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id = 4) as total_turnos_pendientes,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 1 and t.vacuna_id = 4) as turnos_pendientes_uno,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 2 and t.vacuna_id = 4) as turnos_pendientes_dos,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 3 and t.vacuna_id = 4) as turnos_pendientes_tres;",
			nativeQuery = true)
	List<Object[]> getTurnosPendientesGripe();
	
	@Query(value="SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_pendientes\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 1) as total_turnos_pendientes,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 1 and t.vacuna_id IN (1,2,3)) as turnos_pendientes_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 1 and t.vacuna_id = 4) as turnos_pendientes_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 1 and t.vacuna_id = 5) as turnos_pendientes_yellow\r\n"
			+ "UNION\r\n"
			+ "SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_pendientes\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 2) as total_turnos_pendientes,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 2 and t.vacuna_id IN (1,2,3)) as turnos_pendientes_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 2 and t.vacuna_id = 4) as turnos_pendientes_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 2 and t.vacuna_id = 5) as turnos_pendientes_yellow\r\n"
			+ "UNION\r\n"
			+ "SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_pendientes\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 3) as total_turnos_pendientes,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 3 and t.vacuna_id IN (1,2,3)) as turnos_pendientes_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 3 and t.vacuna_id = 4) as turnos_pendientes_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacunatorio_id = 3 and t.vacuna_id = 5) as turnos_pendientes_yellow",
			nativeQuery = true)
	List<Object[]> getTurnosPendientesTodasPorVacunatorio();
	
	@Query(value="SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_pendientes\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.asistio is null) as total_turnos_pendientes,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id IN (1,2,3)) as turnos_pendientes_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id = 4) as turnos_pendientes_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id = 5) as turnos_pendientes_yellow",
			nativeQuery = true)
	List<Object[]> getTurnosPendientesTodas();
	
	@Query(value="SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_covid\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.vacuna_id IN (1,2,3)) as total_turnos_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id IN (1,2,3)) as turnos_pendientes_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio = true and t.vacuna_id IN (1,2,3)) as turnos_asistidos_covid,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio = false and t.vacuna_id IN (1,2,3)) as turnos_no_asistidos_covid",
			nativeQuery = true)
	List<Object[]> getReporteCovid();

	@Query(value="SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_gripe\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.vacuna_id = 4) as total_turnos_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id = 4) as turnos_pendientes_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio = true and t.vacuna_id = 4) as turnos_asistidos_gripe,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio = false and t.vacuna_id = 4) as turnos_no_asistidos_gripe",
			nativeQuery = true)
	List<Object[]> getReporteGripe();
	
	@Query(value="SELECT \r\n"
			+ "(SELECT count(t.id) as turnos_yellow\r\n"
			+ "FROM turno t \r\n"
			+ "WHERE t.vacuna_id = 5) as total_turnos_yellow,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio is null and t.vacuna_id = 5) as turnos_pendientes_yellow,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio = true and t.vacuna_id = 5) as turnos_asistidos_yellow,\r\n"
			+ "(SELECT count(t.id)\r\n"
			+ "FROM turno t\r\n"
			+ "WHERE t.asistio = false and t.vacuna_id = 5) as turnos_no_asistidos_yellow",
			nativeQuery = true)
	List<Object[]> getReporteYellow();
	
	@Query(value="SELECT t.id, t.fecha_aplicacion, p.dni, p.nombre, p.apellido, z.nombre_zona, vac.nombre as nombre_vacunatorio\r\n"
			+ "FROM turno t \r\n"
			+ "INNER JOIN paciente p ON (t.paciente_id = t.id)\r\n"
			+ "INNER JOIN vacunatorio vac ON (t.vacunatorio_id = vac.id)\r\n"
			+ "INNER JOIN zona z ON (z.id = vac.zona_id)\r\n"
			+ "WHERE p.dni = :dni and date(t.fecha_aplicacion) > :fechaHoy and t.asistio is null",
			nativeQuery = true)
	List<Object[]> getTurnosFuturosPorDni(Integer dni, String fechaHoy);
	
	// INICIO REPORTES LISTADO
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id = :vacunaId and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin) and p.dni = :pacienteDni  and t.vacunatorio_id = :vacunatorioId\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteCompleto(Integer vacunaId, String fechaInicio, String fechaFin, Integer vacunatorioId, Integer pacienteDni);
		
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id = :vacunaId and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin) and p.dni = :pacienteDni\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteParcialConDni(Integer vacunaId, String fechaInicio, String fechaFin, Integer pacienteDni);
	
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id = :vacunaId and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin) and t.vacunatorio_id = :vacunatorioId\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteParcialConVacunatorio(Integer vacunaId, String fechaInicio, String fechaFin, Integer vacunatorioId);
	
	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id = :vacunaId and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin)\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteSimple(Integer vacunaId, String fechaInicio, String fechaFin);

	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id IN (1,2,3) and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin)\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteSimpleCovid(String fechaInicio, String fechaFin);

	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id IN (1,2,3) and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin) and t.vacunatorio_id = :vacunatorioId\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteParcialConVacunatorioCovid(String fechaInicio, String fechaFin, Integer vacunatorioId);

	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id IN (1,2,3) and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin) and p.dni = :pacienteDni\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteParcialConDniCovid(String fechaInicio, String fechaFin, Integer pacienteDni);

	@Query(value="SELECT t.id, p.nombre, p.apellido, p.dni, v.nombre as nombre_vacunatorio, vac.nombre as nombre_vacuna,\r\n"
			+ "t.fecha_aplicacion, t.fecha_asignacion, t.asistio\r\n"
			+ "FROM turno t\r\n"
			+ "INNER JOIN paciente p ON (p.id = t.paciente_id)\r\n"
			+ "INNER JOIN vacunatorio v ON (v.id = t.vacunatorio_id)\r\n"
			+ "INNER JOIN vacuna vac ON (vac.id = t.vacuna_id)\r\n"
			+ "WHERE vac.id IN (1,2,3) and (date(t.fecha_aplicacion) > :fechaInicio) and (date(t.fecha_aplicacion) < :fechaFin) and p.dni = :pacienteDni  and t.vacunatorio_id = :vacunatorioId\r\n"
			+ "ORDER BY t.fecha_aplicacion",
			nativeQuery = true)
	List<Object[]> getReporteCompletoCovid(String fechaInicio, String fechaFin, Integer vacunatorioId, Integer pacienteDni);
	
	// FIN REPORTE - LISTADOS
}
