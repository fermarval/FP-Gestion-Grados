package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.*;
import java.util.stream.Collectors;

import fp.grados.tipos.*;

public class Grados {
	
	//B10
	
	public static <T> List<T> leeFichero(String nombreFichero, Function<String,T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero))
					 .map(funcion_deString_aT)
					 .collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error en lectura del fichero: "+nombreFichero);
		}
		
		return res;
	}
	
	//B11
	
//Alumno
	

	private static Set<Alumno> setAlumnos = new HashSet<Alumno>();
	
	//Método creacional parámetros, Java 8
	public static Alumno createAlumno(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email){
		Alumno res = null;
		if(usarJava8)
			res = new AlumnoImpl2(dni,nombre,apellidos,fechaNacimiento,email);
		else
			res = new AlumnoImpl(dni,nombre,apellidos,fechaNacimiento,email);
		actualiza(res);
		return res;
	}

	private static void actualiza(Alumno a) {
		setAlumnos.add(a);
	}

	//Método creacional String, Java 8
	public static Alumno createAlumno(String s){
		Alumno res = null;
		if(usarJava8)
			res = new AlumnoImpl2(s);
		else
			res = new AlumnoImpl(s);
		
		actualiza(res);
		return res;
	}
	
	//Método creacional copia
	public static Alumno createAlumno(Alumno a){
		Alumno res = null;
		if(usarJava8)
			res = new AlumnoImpl2(a.getDNI(), a.getNombre(), a.getApellidos(), a.getFechaNacimiento(), a.getEmail());
		else
			res = new AlumnoImpl(a.getDNI(), a.getNombre(), a.getApellidos(), a.getFechaNacimiento(), a.getEmail());
		completaAlumno(res,a);
		actualiza(res);
		return res;
	}

	private static void completaAlumno(Alumno aNuevo, Alumno aAntiguo) {
		for(Asignatura a: aAntiguo.getAsignaturas())
			aNuevo.matriculaAsignatura(a);
		for(Nota n: aAntiguo.getExpediente().getNotas())
			aNuevo.evaluaAlumno(n.getAsignatura(), n.getCursoAcademico(), n.getConvocatoria(), n.getValor(), n.getMencionHonor());
	}

	//Método creacional fichero
	public static List<Alumno> createAlumnos(String nf){
		List<Alumno> res = leeFichero(nf, s->createAlumno(s));
		return res;
	}
	
	//2 métodos poblacionales
	public static Integer getNumAlumnosCreados(){
		return setAlumnos.size();
	}
	
	public static Set<Alumno> getAlumnosCreados(){
		return new HashSet<Alumno>(setAlumnos);
	}
	
//Asignatura
	private static Integer numAsignaturas = 0;
	private static Set<Asignatura> setAsignaturas = new HashSet<Asignatura>();
	private static Map<String,Asignatura> mapAsignaturas = new HashMap<String,Asignatura>();
	
	//Método creacional parámetros
	public static Asignatura createAsignatura(String nombre, String codigo, Double creditos, TipoAsignatura tipo,
			Integer curso, Departamento departamento){
		Asignatura res = new AsignaturaImpl(nombre,codigo,creditos,tipo,curso,departamento);
		actualiza(res);
		return res;
	}
	
	//Método creacional String
	public static Asignatura createAsignatura(String s){
		Asignatura res = new AsignaturaImpl(s);
		actualiza(res);
		return res;
	}
	
	//Método creacional fichero
	public static List<Asignatura> createAsignaturas(String nf){
		List<Asignatura> res = leeFichero(nf, s->createAsignatura(s));
		return res;
	}

	private static void actualiza(Asignatura a){
		setAsignaturas.add(a);
		if(mapAsignaturas.get(a.getCodigo())==null)
				mapAsignaturas.put(a.getCodigo(), a);
		
		if(!setAsignaturas.contains(a))
			numAsignaturas++;
	}
	
	//4 métodos creacionales
	
	public static Integer getNumAsignaturasCreadas(){
		return setAsignaturas.size();
	}
	
	public static Set<Asignatura> getAsignaturasCreadas(){
		return new HashSet<Asignatura>(setAsignaturas);
	}
	
	public static Asignatura getAsignaturaCreada(String cod){
		Asignatura res;
		if(cod==null);
			res = null;
		res = mapAsignaturas.get(cod);
		return res;
	}
	
	public static Set<String> getCodigosAsignaturasCreadas(){
		return new HashSet<String>(mapAsignaturas.keySet());
	}
	
//Becas
	
	private static Integer numBecas = 0;
	private static Set<Beca> setBecas = new HashSet<Beca>();
	private static Map<TipoBeca,Integer> mapBecas = new HashMap<TipoBeca,Integer>();
	
	//Método creacional parámetros 1
	public static Beca createBeca(String cod, TipoBeca tb){
		Beca res = new BecaImpl(cod,tb);
		actualiza(res);
		return res;
	}
	
	private static void actualiza(Beca b) {
		setBecas.add(b);
		numBecas++;
		
		Integer x = mapBecas.get(b.getTipo());
		if(x == null)
			mapBecas.put(b.getTipo(), 1);
		else
			mapBecas.put(b.getTipo(), x+1);
		
	}
	
	//Método creacional parámetros 2
	public static Beca createBeca(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo){
		Beca res = new BecaImpl(codigo,cuantiaTotal,duracion,tipo);
		actualiza(res);
		return res;
	}
	
	//Método creacional String
	public static Beca createBeca(String s){
		Beca res = new BecaImpl(s);
		actualiza(res);
		return res;
	}
	
	//Método creacional copia
	public static Beca createBeca(Beca b){
		Beca res = new BecaImpl(b.getCodigo(),b.getCuantiaTotal(),b.getDuracion(),b.getTipo());
		actualiza(res);
		return res;
	}
	
	//Método creacional fichero
	public static List<Beca> createBecas(String nf){
		List<Beca> res = leeFichero(nf, s->createBeca(s));
		return res;
	}
	
	//3 métodos poblacionales
	
	public static Integer getNumBecasCreadas(){
		return setBecas.size();
	}
	
	public static Integer getNumBecasTipo(TipoBeca tb){
		Integer res = mapBecas.get(tb);
		if(res == null)
			return 0;
		else
			return res;
	}
	
	public static Set<Beca> getBecasCreadas(){
		return new HashSet<Beca>(setBecas);
	}
	
//Centro
	
	
	private static Set<Centro> setCentros = new HashSet<Centro>();
	private static List<Integer> listPlantas = new ArrayList<Integer>();
	private static List<Integer> listSotanos = new ArrayList<Integer>();
	
	//Método creacional parámetros, Java 8
	public static Centro createCentro(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos){
		Centro res = null;
		if(usarJava8)
			res = new CentroImpl2(nombre,direccion,numeroPlantas,numeroSotanos);
		else
			res = new CentroImpl(nombre,direccion,numeroPlantas,numeroSotanos);
		actualiza(res);
		return res;
	}

	private static void actualiza(Centro c) {
		listPlantas.add(c.getNumeroPlantas());
		listSotanos.add(c.getNumeroSotanos());
		setCentros.add(c);
	}
	
	//Método creacional copia, Java 8
	public static Centro createCentro(Centro c){
		Centro res = null;
		if(usarJava8)
			res = new CentroImpl2(c.getNombre(),c.getDireccion(),c.getNumeroPlantas(),c.getNumeroSotanos());
		else
			res = new CentroImpl(c.getNombre(),c.getDireccion(),c.getNumeroPlantas(),c.getNumeroSotanos());
		completaCentro(res,c);
		actualiza(res);
		return res;
	}
	
	private static void completaCentro(Centro cNuevo, Centro cAntiguo) {
		for(Espacio e: cAntiguo.getEspacios())
			cNuevo.nuevoEspacio(e);
	}

	//6 métodos poblacionales
	
	public static Integer getNumCentrosCreados(){
		return setCentros.size();
	}
	
	public static Set<Centro> getCentrosCreados(){
		return new HashSet<Centro>(setCentros);
	}
	
	public static Integer  getMaxPlantas(){
		Integer res = null;
		if(!setCentros.isEmpty())
			res = Collections.max(listPlantas);
		return res;
	}
	

	
	
	public static Integer  getMaxSotanos(){
		Integer res = null;
		if(!setCentros.isEmpty())
			res = Collections.max(listSotanos);
		return res;
	}
	
	public static Double getMediaPlantas(){
		Double sum = 0.;
		for(Centro c: setCentros){
			sum += c.getNumeroPlantas();
		}
		if(!setCentros.isEmpty())
			sum = sum/setCentros.size();
		return sum;
	}
	 
	public static Double getMediaSotanos(){
		Double sum = 0.;
		for(Centro c: setCentros){
			sum += c.getNumeroSotanos();
		}
		if(!setCentros.isEmpty())
			sum = sum/setCentros.size();
		return sum;
	}
	
//Departamento

	private static Set<Departamento> setDpto = new HashSet<Departamento>();

	//Método creacional parámetros, Java 8
	public static Departamento createDepartamento(String nombre){
		Departamento res = null;
		if(usarJava8)
			res = new DepartamentoImpl2(nombre);
		else
			res = new DepartamentoImpl(nombre);
		actualiza(res);
		return res;
	}

	private static void actualiza(Departamento d) {
		setDpto.add(d);
	}
	
	//2 métodos poblacionales
	
	public static Integer getNumDepartamentosCreados(){
		return setDpto.size(); //return numDptos;
	}
	
	public static Set<Departamento> getDepartamentosCreados(){
		return new HashSet<Departamento>(setDpto);
	}
		
//Espacio
	
	private static SortedSet<Espacio> setEspacios = new TreeSet<Espacio>();
	private static List<Integer> listPlantasEspacios = new ArrayList<Integer>();
	
	//Método creacional parámetros
	public static Espacio createEspacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
		Espacio res = new EspacioImpl(tipo,nombre,capacidad,planta);
		actualiza(res);
		return res;
	}

	private static void actualiza(Espacio e) {
		setEspacios.add(e);
		listPlantasEspacios.add(e.getPlanta());
	}
	
	//Método creacional String
	public static Espacio createEspacio(String s){
		Espacio res = new EspacioImpl(s);
		actualiza(res);
		return res;
	}
	
	//Método creacional copia
	public static Espacio createEspacio(Espacio e){
		Espacio res = new EspacioImpl(e.getTipo(), e.getNombre(), e.getCapacidad(), e.getPlanta());
		actualiza(res);
		return res;
	}
	
	//Método creacional fichero
	public static List<Espacio> createEspacios(String nf){
		List<Espacio> res = leeFichero(nf, s->createEspacio(s));
		return res;
	}
	
	//4 métodos poblacionales
	
	public static Integer getNumEspaciosCreados(){
		return setEspacios.size();
	}
	
	public static SortedSet<Espacio> getEspaciosCreados(){
		return new TreeSet<Espacio>(setEspacios);
	}
	
	public static Integer getPlantaMayorEspacio(){
		Integer res = null;
		if(!setEspacios.isEmpty())
			res = Collections.max(listPlantasEspacios);
		return res;
	}
	
	public static Integer getPlantaMenorEspacio(){
		Integer res = null;
		if(!setEspacios.isEmpty())
			res = Collections.min(listPlantasEspacios);
		return res;
	}
	
//Grado
	
	private static Set<Grado> setGrados = new HashSet<Grado>();
	private static List<Asignatura> setAsignaturasObligatorias = new ArrayList<Asignatura>();
	private static List<Asignatura> setAsignaturasOptativas = new ArrayList<Asignatura>();
	
	//Método creacional parámetros, Java 8
	public static Grado createGrado(String nombre, Set<Asignatura> asignaturasObligatorias, 
			Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas){
		Grado res = null;
		if(usarJava8)
			res = new GradoImpl2(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
		else
			res = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
		
		
		actualiza(res);
		
		return res;
	}

	private static void actualiza(Grado g) {
		setAsignaturasObligatorias.addAll(g.getAsignaturasObligatorias());
		setAsignaturasOptativas.addAll(g.getAsignaturasOptativas());
		setGrados.add(g);
	}
	
	//5 métodos poblacionales
	
	public static Integer getNumGradosCreados(){
		return setGrados.size();
	}
	
	public static Set<Grado> getGradosCreados(){
		return new HashSet<Grado>(setGrados);
	}
	
	public static Double getMediaAsignaturasGrados(){
		Double sum = (double) (setAsignaturasObligatorias.size()+setAsignaturasOptativas.size());
		return sum/setGrados.size();
	}
	
	public static Double getMediaAsignaturasObligatoriasGrados(){
		Double res = (double) (setAsignaturasObligatorias.size());
		return res/setGrados.size();
	}
	
	public static Double getMediaAsignaturasOptativasGrados(){
		Double res = (double) (setAsignaturasOptativas.size());
		return res/setGrados.size();
	}
	
//Profesor
	
	private static Boolean usarProfesorImpl2 = true;
	private static Set<Profesor> setProfesores = new HashSet<Profesor>();
	
	//Método creacional parámetros, Java8
	public static Profesor createProfesor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
			String email, Categoria categoria, Departamento departamento){
		Profesor res = null;
		if(usarProfesorImpl2)
			res = new ProfesorImpl2(dni,nombre,apellidos,fechaNacimiento,email,categoria,departamento);
		else
			res = new ProfesorImpl(dni,nombre,apellidos,fechaNacimiento,email,categoria,departamento);
		
		actualiza(res);
		return res;
	}
	
	private static void actualiza(Profesor p) {
		setProfesores.add(p);
	}
	
	//Método creacional copia
//	public static Profesor createProfesor(Profesor p){
//		Profesor res = createProfesor(p.getDNI(),p.getNombre(),p.getApellidos(),p.getFechaNacimiento(),p.getEmail(),
//				p.getCategoria(),p.getDepartamento());
//
//		completaProfesor(res,p);
//		return res;
//	}
	
	public static Profesor createProfesor(Profesor p){
		Profesor res = null;
		if(usarProfesorImpl2)
			res = new ProfesorImpl2(p.getDNI(),p.getNombre(),p.getApellidos(),p.getFechaNacimiento(),p.getEmail(),
				p.getCategoria(),p.getDepartamento());
		else
			res = new ProfesorImpl(p.getDNI(),p.getNombre(),p.getApellidos(),p.getFechaNacimiento(),p.getEmail(),
					p.getCategoria(),p.getDepartamento());
		completaProfesor(res,p);
		actualiza(res);
		return res;
	}
	

	private static void completaProfesor(Profesor pNuevo, Profesor pAntiguo) {
		for(Asignatura a: pAntiguo.getAsignaturas())
			pNuevo.imparteAsignatura(a, pAntiguo.dedicacionAsignatura(a));
		for(Tutoria t: pAntiguo.getTutorias())
			pNuevo.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
	}

	//3 métodos poblacionales
	
	public static Integer getNumProfesoresCreados(){
		return setProfesores.size();
	}
	
	public static Set<Profesor> getProfesoresCreados(){
		return new HashSet<Profesor>(setProfesores);
	}
	
	public static void setUsarImplementacionMapProfesor(Boolean x){
		usarProfesorImpl2 = x;
	}
	
	//B12
	
	public static Boolean usarJava8 = true;
	
	public static void setUsarJava8(Boolean x){
		usarJava8 = x;
	}
	
	
	
	
	
}