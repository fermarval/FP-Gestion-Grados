package fp.grados.tipos;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl2 extends CentroImpl implements Centro {

	
	public CentroImpl2(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos){
		super(nombre, direccion,numeroPlantas, numeroSotanos);
	}
	
	//B12
	public Espacio getEspacioMayorCapacidad(){
		return getEspacios().stream()
				.max(Comparator.comparing(Espacio::getCapacidad))
				.orElseThrow(ExcepcionCentroOperacionNoPermitida::new);
	}
	
	//B13
	public Integer[] getConteosEspacios(){
		int i = 0;
		Integer[] res = new Integer[TipoEspacio.values().length];
		for(TipoEspacio te: TipoEspacio.values())
			res[i++] = cuentaEspacios(te);
		
		return res;
	}
	
	private Integer cuentaEspacios(TipoEspacio te){
		return (int) getEspacios().stream()
				.filter(e->e.getTipo().equals(te))
				.count();
	}
	
	//B13
	public Set<Despacho> getDespachos(){
		return getEspacios().stream()
				.filter(e -> e instanceof Despacho)
				.map(e -> (Despacho) e)
				.collect(Collectors.toSet());
	}
	
	//B13
	public Set<Despacho> getDespachos(Departamento dpto){
		return getDespachos().stream()
				.filter(d -> pertenece(d,dpto))
				.collect(Collectors.toSet());
	}
	
	private Boolean pertenece(Despacho d, Departamento dpto){
		return d.getProfesores().stream()
				.anyMatch(p->p.getDepartamento().equals(dpto));
	}
	
	//B13
	public Set<Profesor> getProfesores(){
		return getDespachos().stream()
				.flatMap(d->d.getProfesores().stream())
				.collect(Collectors.toSet());
	}
	
	//B13
	public Set<Profesor> getProfesores(Departamento dpto){
		return getProfesores().stream()
				.filter(p->p.getDepartamento().equals(dpto))
				.collect(Collectors.toSet());
	}
	
	//B13
	public SortedMap<Profesor, Despacho> getDespachosPorProfesor(){
		SortedMap<Profesor, Despacho> res = new TreeMap<Profesor, Despacho>();
		getDespachos().stream()
		.forEach(d->actualizaDespachoPorProfesor(res,d));
		return res;
	}
	
	private void actualizaDespachoPorProfesor(SortedMap<Profesor, Despacho> m, Despacho d) {
		d.getProfesores().stream()
			.forEach(p->m.put(p, d));
			
			
	}
}
