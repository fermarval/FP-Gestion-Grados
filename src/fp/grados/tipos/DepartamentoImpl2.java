package fp.grados.tipos;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DepartamentoImpl2 extends DepartamentoImpl implements Departamento{

	public DepartamentoImpl2(String nombre){
		super(nombre);
	}
	
	//B13
	public void borraTutorias(){
		getProfesores().stream()
				.forEach(p->p.borraTutorias());			
	}
	
	//B13
	public void borraTutorias(Categoria cat){
		getProfesores().stream()
			.filter(p->p.getCategoria().equals(cat))
			.forEach(p->p.borraTutorias());
	}
	
	//B13
	public Boolean existeProfesorAsignado(Asignatura a){
		return getProfesores().stream()
				.anyMatch(p -> p.dedicacionAsignatura(a)>0);
	}
	
	//B13
	public Boolean estanTodasAsignaturasAsignadas(){
		return getAsignaturas().stream()
				.allMatch(a -> existeProfesorAsignado(a));
	}
	
	//B13
	public void eliminaAsignacionProfesorado(Asignatura a){
		getProfesores().stream()
			.filter(p -> p.dedicacionAsignatura(a)>0)
			.forEach(p -> p.eliminaAsignatura(a));
	}
	
	//B13
	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor(){
		Function<Profesor,Profesor> f1 = Function.identity();
		Function<Profesor,SortedSet<Tutoria>> f2 = p->p.getTutorias();
		return new TreeMap<>(
				getProfesores().stream()
				.collect(Collectors.toMap(f1,f2)));
	}
}
