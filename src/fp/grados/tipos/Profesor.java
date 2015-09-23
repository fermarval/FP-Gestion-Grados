package fp.grados.tipos;

import java.time.*;
import java.util.List;
import java.util.SortedSet;

public interface Profesor extends Persona{

	Categoria getCategoria();
	SortedSet<Tutoria> getTutorias();
	Departamento getDepartamento();
	List<Asignatura> getAsignaturas();
	List<Double> getCreditos();
	Double dedicacionAsignatura(Asignatura asig);
	Double getDedicacionTotal();
	
	
	void setCategoria(Categoria cat);
	void setDepartamento(Departamento de);
	void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia);
	void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);
	void borraTutorias();
	void imparteAsignatura(Asignatura asig, Double dedicacion);
	void eliminaAsignatura(Asignatura asig);
	
}
