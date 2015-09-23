package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Alumno extends Persona{

	Set<Asignatura> getAsignaturas();
	Integer getCurso();
	Expediente getExpediente();

	void matriculaAsignatura(Asignatura as);
	void eliminaAsignatura(Asignatura as);
	void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota, Boolean mencionHonor);
	void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota);
	
	Boolean estaMatriculadoEn(Asignatura as);
	
	//B9
	SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura();
	
	//B12
	SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso();
}
