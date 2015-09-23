package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor{

	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;
	private Departamento departamento;
	
	private static final Double MAX_CREDITOS = 24.0;
	
	public ProfesorImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Categoria categoria, 
			Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		Integer edad = super.getEdad();
		checkEdad(edad);
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();
		this.asignaturas = new ArrayList<Asignatura>();
		this.creditos = new ArrayList<Double>();
		this.setDepartamento(departamento);
	}
	
	private void checkEdad(Integer edad){
		if(edad<18){
			throw new ExcepcionProfesorNoValido("ProfesorImpl.checkEdad: Un profesor debe ser una persona mayor de edad.");
		}
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
	
	public List<Asignatura> getAsignaturas() {
		return new ArrayList<Asignatura>(asignaturas);
	}

	public List<Double> getCreditos() {
		return new ArrayList<Double>(creditos);
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
		checkUmbral(fn, umbral);
		super.setFechaNacimiento(fn);
	}

	private void checkUmbral(LocalDate fn, LocalDate umbral) {
		if(fn.isAfter(umbral)){
			throw new ExcepcionProfesorNoValido("ProfesorImpl.checkUmbral: La fecha no es válida");
		}
		
	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia) {
		Tutoria tut = new TutoriaImpl(dia, horaComienzo, duracionMinutos);
		tutorias.add(tut);
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		for(Tutoria x : this.getTutorias()){
			checkTutoria(x, horaComienzo, dia);
			tutorias.remove(x);
		}
	}

	private void checkTutoria(Tutoria x, LocalTime horaComienzo, DayOfWeek dia) {
		if(!(x.getHoraComienzo().equals(horaComienzo) && x.getDiaSemana().equals(dia))){
			throw new UnsupportedOperationException("ProfesorImpl.checkTutoria: El profesor no tiene esa tutoria");
		}
		
	}

	public void borraTutorias() {
		tutorias.clear();
	}
	
	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		
		if(this.departamento!=null)
			checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura(asig, dedicacion);
		
		if(asignaturas.contains(asig)){
			
			actualizaDedicacion(asig, dedicacion);
			
		}else{
			nuevaAsignatura(asig, dedicacion);
		}
	}
	
	private void checkAsignaturaDepartamento(Asignatura asig) {
		if(!this.getDepartamento().equals(asig.getDepartamento())){
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl.AsignaturaDepartamento: La asignatura debe ser del departamento "+this.getDepartamento());
		}
	}
	
	private void checkCreditosAsignatura(Asignatura asig, Double dedicacion) {
		if(!(dedicacion>0 && dedicacion<=asig.getCreditos())){
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl.checkCreditosAsignatura: La dedicación de la asignatura debe ser inferior a "+asig.getCreditos());
		}
	}

	private void actualizaDedicacion(Asignatura asig, Double dedicacion) {
		creditos.set(asignaturas.indexOf(asig), dedicacion);
		checkDedicacion2();
	}
	
	private void nuevaAsignatura(Asignatura asig, Double dedicacion) {
		checkDedicacion(dedicacion);
		asignaturas.add(asig);
		creditos.add(dedicacion);
	}
	
	private void checkDedicacion(Double dedicacion) {
		if(getDedicacionTotal() + dedicacion > MAX_CREDITOS){
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl.checkDedicacion: La dedicación sobrepasa los "+MAX_CREDITOS+" créditos.");
		}
	}
	
	private void checkDedicacion2() {
		if(getDedicacionTotal() > MAX_CREDITOS){
			throw new ExcepcionProfesorOperacionNoPermitida("ProfesorImpl.checkDedicacion2: La dedicación sobrepasa los "+MAX_CREDITOS+" créditos.");
		}
	}

	public void eliminaAsignatura(Asignatura asig) {
		if(asignaturas.contains(asig)){
			creditos.remove(asignaturas.indexOf(asig)); //Elimina el credito correspondiente a la asignatura
			asignaturas.remove(asig);
		}
	}

	public Double dedicacionAsignatura(Asignatura asig){
		Double res = 0.;
		for(Asignatura a: this.getAsignaturas()){
			if(a.equals(asig)){
				res = creditos.get(asignaturas.indexOf(asig));
			}
		}
		return res;
	}
	
	//“28200400P – Martín Oviedo, María – 21/05/1985 (TITULAR)”
	public String toString(){
		return super.toString()+" ("+this.getCategoria()+")";
	}
}
