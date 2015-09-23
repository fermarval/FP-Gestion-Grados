package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno{

	private Set<Asignatura> asignaturas;
	private Expediente expediente;
	
	public AlumnoImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		
		this.asignaturas = new HashSet<Asignatura>();
		checkEmail(email);
		this.expediente = new ExpedienteImpl();
	}
	
	//B10
	public AlumnoImpl(String s){
		super(s);
		checkEmail(super.getEmail());
		this.asignaturas = new HashSet<Asignatura>();
		this.expediente = new ExpedienteImpl();
	}
	
	private void checkEmail(String ema){
		
		if(!ema.endsWith("@alum.us.es")){
			throw new ExcepcionAlumnoNoValido("AlumnoImpl.checkEmail: El email del alumno no contiene @alum.us.es");
		}	
	}

	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public Integer getCurso() {
		Integer res = 0;
		for(Asignatura as: asignaturas){
			if(res < as.getCurso()){
				res = as.getCurso();
			}
		}
		return res;
	}
	
	public Expediente getExpediente() {
		return expediente;
	}
	
	public void setEmail(String ema){
		checkEmail(ema);
		super.setEmail(ema);
	}

	public void matriculaAsignatura(Asignatura as) {
		if(estaMatriculadoEn(as)){
			throw new ExcepcionAlumnoOperacionNoPermitida("AlumnoImpl.matriculaAsignatura: El alumno ya está matriculado en esa asignatura.");
		}
		this.asignaturas.add(as);
	}

	public void eliminaAsignatura(Asignatura as) {
		if(!estaMatriculadoEn(as)){
			throw new ExcepcionAlumnoOperacionNoPermitida("AlumnoImpl.matriculaAsignatura: El alumno no está matriculado en esa asignatura.");
		}
		this.asignaturas.remove(as);
	}
	
	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencionHonor) {
		if(!this.getAsignaturas().contains(a)){
			throw new ExcepcionAlumnoOperacionNoPermitida("AlumnoImpl.evaluaAlumno: El alumno no está matriculado en esa asignatura.");
		}
		Nota n = new NotaImpl(a, curso, convocatoria, nota, mencionHonor);
		this.getExpediente().nuevaNota(n);
		
	}

	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota) {
		checkAsignatura(a);
		nuevaNota(a, curso, convocatoria, nota);
	}


	private void nuevaNota(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota) {
		Nota n = new NotaImpl(a, curso, convocatoria, nota);
		this.getExpediente().nuevaNota(n);
	}

	private void checkAsignatura(Asignatura a) {
		if(!this.getAsignaturas().contains(a)){
			throw new ExcepcionAlumnoOperacionNoPermitida("AlumnoImpl.checkAsignatura: El alumno no está matriculado en esa asignatura.");
		}
	}

	public Boolean estaMatriculadoEn(Asignatura as) {
		Boolean res = false;
		if(getAsignaturas().contains(as)){
			res = true;
		}
		return res;
	}
	
	//“(1º) 28864657W – García Vaquero, Pascual – 15/09/1998”.
	public String toString(){
		return "("+this.getCurso()+"º) "+super.toString();
	}
	
	//B9
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		SortedMap<Asignatura, Calificacion> res = new TreeMap<Asignatura, Calificacion>();
		
		for(Nota n: getExpediente().getNotas())
			actualizaCalificacionPorAsignatura(res,n);
		
		return res;
	}

	private void actualizaCalificacionPorAsignatura(SortedMap<Asignatura, Calificacion> m, Nota n) {
		Calificacion c = m.get(n.getAsignatura());
		
		if(c == null || c.compareTo(n.getCalificacion())<0){
			m.put(n.getAsignatura(), n.getCalificacion());	
		}
	}
	
	//B12
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso(){
		SortedSet<Asignatura> res = new TreeSet<>
		(Comparator.comparing(Asignatura::getCurso).reversed().thenComparing(Comparator.naturalOrder()));
		res.addAll(getAsignaturas());
		return res;
	}
}
