package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;




public class GradoImpl2 extends GradoImpl implements Grado{
	
	public GradoImpl2(String nombre, Set<Asignatura> asignaturasObligatorias, 
			Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas){
		super(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
	}
	
	//B13
	public Double getNumeroTotalCreditos(){
		return getAsignaturasObligatorias().stream()
				.mapToDouble(a->a.getCreditos())
				.sum() + getNumeroMinimoCreditosOptativas();
	}

	//B13
	public Set<Departamento> getDepartamentos(){
		return todasAsignaturas().stream()
				.map(a->a.getDepartamento())
				.collect(Collectors.toSet());
				
	}
	
	private Set<Asignatura> todasAsignaturas(){
		Set<Asignatura> todasAsig = getAsignaturasObligatorias();
		todasAsig.addAll(getAsignaturasOptativas());
		return todasAsig;
	}
	
	//B13
	public Set<Profesor> getProfesores() {
		return getDepartamentos().stream()
				.flatMap(d->d.getProfesores().stream())
				.collect(Collectors.toSet());
	}
	
	//B13
	public Set<Asignatura> getAsignaturas(Integer curso) {
		return todasAsignaturas().stream()
				.filter(a->a.getCurso().equals(curso))
				.collect(Collectors.toSet());
	}

	//B13
	
	public Asignatura getAsignatura(String codigo) {
		return todasAsignaturas().stream()
				.filter(a->a.getCodigo().equals(codigo))
				.findAny().get();
	}

//	//B13
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		todasAsignaturas().stream()
			.forEach(a->res.put(a, a.getCreditos()));
		return res;
	}
}
