package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho {

	private Set<Profesor> profesores = new HashSet<Profesor>();
	
	public DespachoImpl(String nombre, Integer capacidad,
			Integer planta, Set<Profesor> profesores) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		checkCapacidad(profesores, capacidad);
		this.profesores = profesores;
	}
	
	public DespachoImpl(String nombre, Integer capacidad,
			Integer planta, Profesor profesor) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores.add(profesor);
	}
	
	public DespachoImpl(String nombre, Integer capacidad,
			Integer planta) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		
	}
	
	//B10
	public DespachoImpl(String s){
		super(s+",OTRO");
		this.profesores = new HashSet<Profesor>();
	}
	
	private void checkCapacidad(Set<Profesor> profesores, Integer capacidad){
		if(profesores.size() > capacidad){
			throw new ExcepcionDespachoNoValido("DespachoImpl.checkCapacidad: No puede haber más de "+capacidad+" profesores en el despacho.");
		}
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> setPr) {
		checkCapacidad(setPr, this.getCapacidad());
		this.profesores = setPr;
	}
	
	public void setTipo(TipoEspacio tipo){
		throw new UnsupportedOperationException("DespachoImpl.setTipo: El tipo de un despacho siempre es OTRO.");
	}
	
	//“M2.25 (planta 2) [28200400P – Martín Oviedo, María – 21/05/1985 (TITULAR), 33123210J – Vegarredonda Ordiales, Jorge – 25/11/1990 (CONTRATADO_DOCTOR)]”
	public String toString(){
		return super.toString()+" "+this.getProfesores().toString();
	}
}
