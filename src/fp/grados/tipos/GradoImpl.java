package fp.grados.tipos;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado{

	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double numeroMinimoCreditosOptativas;
	
	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias, 
			Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas){
		checkOptativas(asignaturasOptativas);
		checkCreditos(asignaturasOptativas, numeroMinimoCreditosOptativas);
		this.nombre = nombre;
		this.asignaturasObligatorias = asignaturasObligatorias;
		this.asignaturasOptativas = asignaturasOptativas;
		this.numeroMinimoCreditosOptativas = numeroMinimoCreditosOptativas;
	}

	private void checkOptativas(Set<Asignatura> asignaturasOptativas){
		Double aux = 0.;
		for(Asignatura a : asignaturasOptativas){
			if(aux == 0.){
				aux = a.getCreditos();
			}else{
				if(!(aux.equals(a.getCreditos()))){
					throw new ExcepcionGradoNoValido("GradoImpl.checkOptativas: Todas las optativas deben tener el mismo numero de créditos.");
				}
			}
		}	
	}
	
	private void checkCreditos(Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas) {
		if(numeroMinimoCreditosOptativas < 0. || numeroTotalCreditosOptativas(asignaturasOptativas) < numeroMinimoCreditosOptativas){
			throw new ExcepcionGradoNoValido("GradoImpl.checkCreditos: El numero mínimo de credito de las optativas debe ser mayor de "+numeroMinimoCreditosOptativas);
		}
	}
	
	private Double numeroTotalCreditosOptativas(Set<Asignatura> asignaturasOptativas) {
		Double res = 0.;
		for(Asignatura as : asignaturasOptativas){
			res  += as.getCreditos(); 
		}
		return res;
	}

	public String getNombre() {
		return nombre;
	}

	public Set<Asignatura> getAsignaturasObligatorias() {
		return new HashSet<Asignatura>(asignaturasObligatorias);
	}

	public Set<Asignatura> getAsignaturasOptativas() {
		return new HashSet<Asignatura>(asignaturasOptativas);
	}

	public Double getNumeroMinimoCreditosOptativas() {
		return numeroMinimoCreditosOptativas;
	}

	public Double getNumeroTotalCreditos() {
		Double res = 0.;
		for(Asignatura as : this.getAsignaturasObligatorias()){
			res  += as.getCreditos(); 
		}
		return res + this.getNumeroMinimoCreditosOptativas();
	}

	public Set<Departamento> getDepartamentos() {
		Set<Departamento> res = new HashSet<Departamento>();
		for(Asignatura x: todasAsignaturas()){
			if(x != null){
				res.add(x.getDepartamento());
			}
		}
		return res;
	}
	
	private Set<Asignatura> todasAsignaturas(){
		Set<Asignatura> todasAsig = getAsignaturasObligatorias();
		todasAsig.addAll(getAsignaturasOptativas());
		return todasAsig;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> res = new HashSet<Profesor>();
		for(Departamento x: this.getDepartamentos()){
			if(x != null){
				res.addAll(x.getProfesores());
			}
		}
		return res;
	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> res = new HashSet<Asignatura>();
		for(Asignatura x: todasAsignaturas()){
			if(x.getCurso().equals(curso)){
				res.add(x);
			}
		}
		return res;
	}

	public Asignatura getAsignatura(String codigo) {
		Asignatura res = null;
		for(Asignatura x: todasAsignaturas()){
			if(x.getCodigo().equals(codigo)){
				Boolean bus = true;
				if(bus){
					res = x;
					break;
				}
			}
		}
		return res;
	}
	
	//Igualdad: nombre
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Grado){
			Grado g = (Grado) o;
			res = this.getNombre().equals(g.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}

	//Ordenación: nombre
	public int compareTo(Grado g) {
		int res = getNombre().compareTo(g.getNombre());
		return res;
	}	
	
	//Representación: nombre
	public String toString(){
		return getNombre();
	}

	//B9
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		for(Asignatura a: todasAsignaturas()){
			res.put(a, a.getCreditos());
		}
		return res;
	}
	
	//B12
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {
		Comparator<Departamento> cmp1 = Comparator.comparing(d -> d.getAsignaturas().size());
		Comparator<Departamento> cmp2 = cmp1.reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Departamento> res = new TreeSet<Departamento>(cmp2);
		res.addAll(getDepartamentos());
		return res;
	}
}
