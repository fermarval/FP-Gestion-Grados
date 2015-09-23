package fp.grados.utiles.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestGrados {
public static void main(String[] args) {
	
	Grados.usarJava8 = false;
	
	Asignatura aa = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,null);
	Grados.createAlumno("12345678Z", "Juaan", "Nadie Nadie",
			LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	Grados.createAlumno("12345678Z,Juan,Lopez Garcia,20/07/1998,juan@alum.us.es");
	Alumno al = new AlumnoImpl("12345678Z", "Juaaan", "Nadie Nadie",
			LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	Grados.createAlumnos("res/alumnos.txt");
	al.matriculaAsignatura(aa);
	Grados.createAlumno(al);

	
	System.out.println("Alumnos creados: "+Grados.getNumAlumnosCreados());
	System.out.println("Alumnos creados: "+Grados.getAlumnosCreados()+"\n");
	
	Grados.createAsignatura("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,null);
	Grados.createAsignatura("Calculo Infinitesimal y Numerico# 2050002# 6.0# PRIMER_CUATRIMESTRE# 1");
	Grados.createAsignaturas("res/asignaturas.txt");
	
	System.out.println("Asignaturas creadas: "+Grados.getNumAsignaturasCreadas());
	System.out.println("Asignaturas creadas: "+Grados.getAsignaturasCreadas());
	System.out.println("Asignatura código 2050002: "+Grados.getAsignaturaCreada("2050002"));
	System.out.println("Codigos asignaturas creadas: "+Grados.getCodigosAsignaturasCreadas()+"\n");
	
	Grados.createBeca("ABC1234",TipoBeca.ORDINARIA);
	Grados.createBeca("ABC1438",10000.0,6,TipoBeca.ORDINARIA);
	Grados.createBeca("EMP7010,3000.0,6,EMPRESA");
	Beca be = new BecaImpl("ABC1468",10000.0,6,TipoBeca.ORDINARIA);
	Grados.createBeca(be);
	Grados.createBecas("res/becas.txt");
	
	System.out.println("Becas creadas: "+Grados.getNumBecasCreadas());
	System.out.println("Número becas tipo ORDINARIA: "+Grados.getNumBecasTipo(TipoBeca.ORDINARIA));
	System.out.println("Becas creadas: "+Grados.getBecasCreadas()+"\n");
	
	Grados.createCentro("ETSA", "Reina Mercedes", 2, 2);
	Centro ce = new CentroImpl("ETSII", "Reina Mercedes", 4, 1);
	Grados.createCentro(ce);
	
	System.out.println("Centros creados: "+Grados.getNumCentrosCreados());
	System.out.println("Centros creados: "+Grados.getCentrosCreados());
	System.out.println("Máx plantas: "+Grados.getMaxPlantas());
	System.out.println("Máx sótanos: "+Grados.getMaxSotanos());
	System.out.println("Media plantas: "+Grados.getMediaPlantas());
	System.out.println("Media sótanos: "+Grados.getMediaSotanos()+"\n");

	Grados.createDepartamento("Departamento 1");
	
	System.out.println("Departamentos creados: "+Grados.getNumDepartamentosCreados());
	System.out.println("Departamentos creados: "+Grados.getDepartamentosCreados()+"\n");

	Grados.createEspacio(TipoEspacio.EXAMEN, "Aula examen", 90, 2);
	Espacio es = new EspacioImpl(TipoEspacio.EXAMEN, "Aula Estudio", 60, 1);
	Grados.createEspacio(es);
	Grados.createEspacio("A3.11,3,170,EXAMEN");
	Grados.createEspacios("res/espacios.txt");
	
	System.out.println("Espacios creados: "+Grados.getNumEspaciosCreados());
	System.out.println("Espacios creados: "+Grados.getEspaciosCreados());
	System.out.println("Planta mayor espacio: "+Grados.getPlantaMayorEspacio());
	System.out.println("Planta menor espacio: "+Grados.getPlantaMenorEspacio()+"\n");
	
	Departamento d1 = new DepartamentoImpl("Departamento 1");
	
	Asignatura a1 = new AsignaturaImpl("Asignatura 1","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
	Asignatura a2 = new AsignaturaImpl("Asignatura 2","2050002",6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d1);
	Asignatura a3 = new AsignaturaImpl("Asignatura 3","2050003",6.0, TipoAsignatura.SEGUNDO_CUATRIMESTRE, 2,d1);
	Asignatura a4 = new AsignaturaImpl("Asignatura 4","2050004",12.0, TipoAsignatura.ANUAL, 1,d1);
	Asignatura a5 = new AsignaturaImpl("Asignatura 5","2050005",12.0, TipoAsignatura.ANUAL, 2,d1);
	
	Set<Asignatura> setOb = new HashSet<Asignatura>();
	setOb.add(a1);setOb.add(a2);setOb.add(a3);
	
	Set<Asignatura> setOp = new HashSet<Asignatura>();
	setOp.add(a4);setOp.add(a5);
	
	Grados.createGrado("Grado 1", setOb, setOp, 0.);
	Grados.createGrado("Grado 2", setOb, setOp, 0.);
	Grados.createGrado("Grado 3", setOb, setOp, 0.);
	
	System.out.println("Grados creados: "+Grados.getNumGradosCreados());
	System.out.println("Grados creados: "+Grados.getGradosCreados());
	System.out.println("Media asignaturas grados: "+Grados.getMediaAsignaturasGrados());
	System.out.println("Media asignaturas obligatorias grados: "+Grados.getMediaAsignaturasObligatoriasGrados());
	System.out.println("Media asignaturas optativas grados: "+Grados.getMediaAsignaturasOptativasGrados()+"\n");

	Grados.createProfesor("53344582S", "Juan", "Solo", LocalDate.of(1991, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, d1);
	Profesor pr = new ProfesorImpl("53344582S", "Juaan", "Solo", LocalDate.of(1991, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, d1);
	Grados.createProfesor(pr);
	
	System.out.println("Profesores creados: "+Grados.getNumProfesoresCreados());
	System.out.println("Profesores creados: "+Grados.getProfesoresCreados());
}
}
