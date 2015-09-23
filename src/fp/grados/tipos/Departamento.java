package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Departamento extends Comparable<Departamento> {
	
	String getNombre();
	Set<Profesor> getProfesores();
	Set<Asignatura> getAsignaturas();

	void nuevoProfesor(Profesor p);
	void eliminaProfesor(Profesor p);
	void nuevaAsignatura(Asignatura a);
	void eliminaAsignatura(Asignatura a);
	
	void borraTutorias();
	void borraTutorias(Categoria c);
	Boolean existeProfesorAsignado(Asignatura a);
	Boolean estanTodasAsignaturasAsignadas();
	void eliminaAsignacionProfesorado(Asignatura a);
	
	//B9
	SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura();
	SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor();
	
	//B12
	Profesor getProfesorMaximaDedicacionMediaPorAsignatura();
}