package fp.grados.tipos;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {

	private String nombre, direccion;
	private Integer numeroPlantas, numeroSotanos;
	private Set<Espacio> espacios;
	
	public CentroImpl(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos){
		
		this.nombre = nombre;
		this.direccion = direccion;
		checkNumeroPlantas(numeroPlantas);
		this.numeroPlantas = numeroPlantas;
		checkNumeroSotanos(numeroSotanos);
		this.numeroSotanos = numeroSotanos;
		this.espacios = new HashSet<Espacio>();
	}
	
	private void checkNumeroPlantas(Integer numP){
		if(numP<1){
			throw new ExcepcionCentroNoValido("CentroImpl.checkNumeroPlantas: Un centro debe tener al menos una planta.");
		}
	}
	
	private void checkNumeroSotanos(Integer numS){
		if(numS<0){
			throw new ExcepcionCentroNoValido("CentroImpl.checkNumeroSotanos: Un centro debe tener cero o más sótanos.");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getNumeroPlantas() {
		return numeroPlantas;
	}

	public Integer getNumeroSotanos() {
		return numeroSotanos;
	}

	public Set<Espacio> getEspacios() {
		return new HashSet<Espacio>(espacios);
	}
	
	public Integer[] getConteosEspacios() {
		Integer[] res = new Integer[5];
		Integer teo = 0, lab = 0, sem = 0, 
				exa = 0, otr = 0;
		
		for(Espacio x: this.getEspacios()){
			if(x.getTipo()==TipoEspacio.TEORIA)
				teo++;
			if(x.getTipo()==TipoEspacio.LABORATORIO)
				lab++;
			if(x.getTipo()==TipoEspacio.SEMINARIO)
				sem++;
			if(x.getTipo()==TipoEspacio.EXAMEN)
				exa++;
			if(x.getTipo()==TipoEspacio.OTRO)
				otr++;
		}
		res[0]=teo; res[1]=lab; res[2]=sem;
		res[3]=exa; res[4]=otr;
		
		return res;
	}

	public Set<Despacho> getDespachos() {
		Set<Despacho> res = new HashSet<Despacho>();
		for(Espacio x: this.getEspacios()){ //Recorre los espacios
			if(x instanceof Despacho){ //Si un espacio es despacho, se añade
				Despacho d = (Despacho) x;//Se debe castear
				res.add(d);
			}
		}
		return res;
	}

	private boolean existeProfesorEnDepartamento(Despacho des, Departamento d){
		boolean res = false;
		for(Profesor x: des.getProfesores()){ //Recorre los profesores
			if(x.getDepartamento().equals(d)){ //Si un profesor es del departamento dado, true
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Set<Despacho> getDespachos(Departamento d) {
		Set<Despacho> res = new HashSet<Despacho>();
		for(Despacho x:this.getDespachos()){ //Recorre los despachos filtrados de los espacios
			if(existeProfesorEnDepartamento(x,d)==true){ //Si cumple el existe, se añade
				res.add(x);
			}
		}
		return res;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> res = new HashSet<Profesor>();
		for(Despacho x: this.getDespachos()){ //Recorre los despachos filtrados de los espacios
			res.addAll(x.getProfesores()); //Sin filtro, añade todos los profesores de cada despacho
		}
		return res;
	}

	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> res = new HashSet<Profesor>();
		for(Profesor x: this.getProfesores()){ //Recorre los profesores de todos los despachos
			if(x.getDepartamento().equals(d)){ //Si un profesor es del departamento dado, se añade
				res.add(x);
			}
		}
		return res;
	}

	public Espacio getEspacioMayorCapacidad() {
		Espacio max = null;
		for(Espacio e: espacios){
			if (max == null || e.getCapacidad()>max.getCapacidad()){
				max = e;
			}
		}
		if(max == null)
			throw new ExcepcionCentroOperacionNoPermitida("CentroImpl.getEspacioMayorCapacidad: No hay espacios.");
		return max;
	}

	public void nuevoEspacio(Espacio e) {
		checkPlantaValida(e.getPlanta());
		espacios.add(e);
	}
	
	private void checkPlantaValida(Integer numP){
		if(-numeroSotanos>numP || numP>numeroPlantas-1){ //Intervalo [-s,p-1]
			throw new ExcepcionCentroOperacionNoPermitida("CentroImpl.checkPlantaValida: El numero de planta "+numP+" no está entre el intervalo [-"+numeroSotanos+","+numeroPlantas+"-1]");
		}
	}

	public void eliminaEspacio(Espacio e) {
		this.espacios.remove(e);
	}

	//Criterio de igualdad: nombre
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Centro){
			Centro ce = (Centro) o;
			res = this.getNombre().equals(ce.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getNombre().hashCode();
	}
	
	//Orden natural: nombre
	
	public int compareTo(Centro ce) {
		int res = this.getNombre().compareTo(ce.getNombre());
		return res;
	}
	
	//Escuela Técnica Superior de Ingeniería Informática
	public String toString(){
		return this.getNombre();
	}
	
	//B9
	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> res = new TreeMap<Profesor, Despacho>();
		for(Despacho d: getDespachos())
			actualizaDespachoPorProfesor(res,d);
		return res;
	}

	private void actualizaDespachoPorProfesor(SortedMap<Profesor, Despacho> m, Despacho d) {
		for(Profesor p: d.getProfesores())
			m.put(p,d); //Actualiza el map añadiendo una nueva entrada
	}
	
	//B12
	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad(){
		Comparator<Espacio> cmp1 = Comparator.comparing(Espacio::getCapacidad).reversed();
		Comparator<Espacio> cmp2 = cmp1.thenComparing(Comparator.naturalOrder());
		
		SortedSet<Espacio> res = new TreeSet<Espacio>(cmp2);
		res.addAll(getEspacios());
		return res;
	}

}
