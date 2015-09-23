package fp.grados.tipos;

import java.util.*;

public interface Grado extends Comparable<Grado>{

	String getNombre();
	Set<Asignatura> getAsignaturasObligatorias();
	Set<Asignatura> getAsignaturasOptativas();
	Double getNumeroMinimoCreditosOptativas();
	Double getNumeroTotalCreditos();
	Set<Departamento> getDepartamentos();
	Set<Profesor> getProfesores();
	
	Set<Asignatura> getAsignaturas(Integer curso);
	Asignatura getAsignatura(String codigo);
	
	//B9
	SortedMap<Asignatura, Double> getCreditosPorAsignatura();
	
	//B12
	SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas();
	
}
