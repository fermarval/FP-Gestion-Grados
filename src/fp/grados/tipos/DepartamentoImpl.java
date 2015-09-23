package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class DepartamentoImpl implements Departamento {

	private String nombre;
	private Set<Profesor> profesores;
	private Set<Asignatura> asignaturas;
	
	public DepartamentoImpl(String nombre){
		this.nombre = nombre;
		this.profesores = new HashSet<Profesor>();
		this.asignaturas = new HashSet<Asignatura>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor>(profesores);
	}

	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura>(asignaturas);
	}

	public void nuevoProfesor(Profesor p) {
		this.profesores.add(p);
		p.setDepartamento(this);
	}
	
	public void eliminaProfesor(Profesor p) {
		this.profesores.remove(p);
		p.setDepartamento(null);
	}

	public void nuevaAsignatura(Asignatura a) {
		this.asignaturas.add(a);
		a.setDepartamento(this);
	}
	
	public void eliminaAsignatura(Asignatura a) {
		this.asignaturas.remove(a);
		a.setDepartamento(null);
		
	}
	
	public void borraTutorias() {
		for(Profesor x: this.getProfesores()){ //Recorre la lista de profesores
			x.borraTutorias(); //Sin filtro, borra todas las tutorias
		}
	}

	public void borraTutorias(Categoria c) {
		for(Profesor x: this.getProfesores()){ //Recorre la lista de profesores
			if(x.getCategoria().equals(c)){ //Si la categoria es la dada
				x.borraTutorias(); //Borra todas las tutorias
			}
		}
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean res = false;
		for(Profesor x: this.getProfesores()){ //Recorre la lista de profesores
			if(x.getAsignaturas().contains(a)){ //Si la asignatura a esta contenida en la lista de asignaturas, true
				res = true;
				if(res){ //Si existe almenos 1, break
					break;
				}
			}
		}
		return res;
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		for(Asignatura x: this.getAsignaturas()){ //Recorre la lista de profesores
			if(!existeProfesorAsignado(x)){ //Si no existe profesor asignado, false
				res = false;
				if(!res){ //Si no existe uno, no existen todos, break
					break;
				}
			}
		}
		return res;
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		for(Profesor p: getProfesores()){
			if(p.dedicacionAsignatura(a)> 0.0)
				p.eliminaAsignatura(a);
		}
	}

	//Criterio de igualdad: nombre
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Departamento){
			Departamento de = (Departamento) o;
			res = this.getNombre().equals(de.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getNombre().hashCode();
	}
	
	//Orden natural: nombre
	public int compareTo(Departamento de) {
		int res = this.getNombre().compareTo(de.getNombre());
		return res;
	}
	
	//Lenguajes y Sistemas Informáticos”.
	public String toString(){
		return this.getNombre();
	}

	//B9
	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> res = new TreeMap<Asignatura, SortedSet<Profesor>>();
		
		for(Profesor p: getProfesores())
			actualizaProfesoresPorAsignatura(res, p);
			
		return res;
	}

	private void actualizaProfesoresPorAsignatura(SortedMap<Asignatura, SortedSet<Profesor>> m, Profesor p) {
		for(Asignatura a: p.getAsignaturas()){
			SortedSet<Profesor> sp = m.get(a);
			if(sp ==null){
				sp = new TreeSet<Profesor>();
			}
			sp.add(p);
			m.put(a, sp);
		}	
	}
	
	//B9
	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor(){
		SortedMap<Profesor, SortedSet<Tutoria>> res = new TreeMap<Profesor, SortedSet<Tutoria>>();
		
		for(Profesor p: getProfesores())
			res.put(p, p.getTutorias());
		
		return res;
	}

	//B12
	public Profesor getProfesorMaximaDedicacionMediaPorAsignatura(){
		return profesores.stream()
				.filter(profe -> !profe.getAsignaturas().isEmpty())
				.max(Comparator.comparing(profe -> profe.getDedicacionTotal() / profe.getAsignaturas().size()))
				.orElseThrow(NoSuchElementException::new);
	}
}
