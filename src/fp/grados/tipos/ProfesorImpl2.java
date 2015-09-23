package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl2 extends PersonaImpl implements Profesor{

	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private Departamento departamento;
	private Map<Asignatura,Double> mAC; //B9
	
	private static final Double MAX_CREDITOS = 24.0;
	
	public ProfesorImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Categoria categoria, 
			Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);

		Integer edad = super.getEdad();
		if(!checkEdad(edad)){
			throw new ExcepcionProfesorNoValido("ProfesorImpl.ProfesorImpl: Un profesor debe ser una persona mayor de edad.");
		}
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();
		this.setDepartamento(departamento);
		
		mAC = new HashMap<Asignatura, Double>(); //B9
	}
	
	private boolean checkEdad(Integer edad){
		boolean res = true;
		if(edad<18){
			res = false;
		}
		return res;
	}

	public Categoria getCategoria() {	
		return categoria;
	}

	public SortedSet<Tutoria> getTutorias() {
		return tutorias;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	//B9
	public List<Asignatura> getAsignaturas() {
		return new ArrayList<Asignatura>(mAC.keySet());
	}

	//B9
	public List<Double> getCreditos() {
		return new ArrayList<Double>(mAC.values());
	}

	public Double getDedicacionTotal() {
		Double res = 0.;
		for(Double x: this.getCreditos()){
			res += x;
		}
		return res;
	}
	
	public void setCategoria(Categoria cat) {
		this.categoria = cat;
		
	}

	public void setDepartamento(Departamento de){
		
		if(de != this.departamento){ //Si no es el mismo
			Departamento antDe = this.departamento; //Actualizamos el departamento
			this.departamento = de;
			
			if(antDe != null){ //Si ya tenia departamento
				antDe.eliminaProfesor(this);
			}
			
			if(de != null){
				de.nuevoProfesor(this);
			}
		}
	}
	
	public void setFechaNacimiento(LocalDate fn){
		LocalDate umbral = LocalDate.now().minusYears(18);
		if(fn.isAfter(umbral)){
			throw new ExcepcionProfesorNoValido("ProfesorImpl.setFechaNacimiento: La fecha no es válida");
		}
		super.setFechaNacimiento(fn);
	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia) {
		Tutoria tut = new TutoriaImpl(dia, horaComienzo, duracionMinutos);
		this.getTutorias().add(tut);
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		for(Tutoria x : this.getTutorias()){
			if(x.getHoraComienzo().equals(horaComienzo) && x.getDiaSemana().equals(dia)){
				this.getTutorias().remove(x);
			}else{
				throw new UnsupportedOperationException("ProfesorImpl2.borraTutoria: El profesor no tiene esa tutoria");
			}
		}
	}

	public void borraTutorias() {
		this.getTutorias().clear();
	}
	
	//B9
	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		
		checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura(asig, dedicacion);
		
		Double ndt = getDedicacionTotal()-dedicacionAsignatura(asig)+dedicacion;
		if(ndt>MAX_CREDITOS)
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl2.imparteAsignatura: El número de créditos no es correcto");
		
		mAC.put(asig,dedicacion);
	}

	private void checkCreditosAsignatura(Asignatura asig, Double dedicacion) {
		if(!(dedicacion>0 && dedicacion<=asig.getCreditos())){
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl2.imparteAsignatura: La dedicación de la asignatura es errónea.");
		}
	}
	
	private void checkAsignaturaDepartamento(Asignatura asig) {
		if(asig.getDepartamento()!=null && this.getDepartamento()!=null && !this.getDepartamento().getAsignaturas().contains(asig)){
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl2.imparteAsignatura: El departamento de la asignatura a "
					+ "impartir no corresponde con el departamento del profesor");
		}
	}

	//B9
	public void eliminaAsignatura(Asignatura asig) {
		mAC.remove(asig);
	}
	
	//B9
	public Double dedicacionAsignatura(Asignatura asig){
		
		Double res = mAC.get(asig);
		
		if(res == null)
			res = 0.0;
		
		return res;
	}
	
	//“28200400P – Martín Oviedo, María – 21/05/1985 (TITULAR)”
	public String toString(){
		return super.toString()+" ("+this.getCategoria()+")";
	}
}
