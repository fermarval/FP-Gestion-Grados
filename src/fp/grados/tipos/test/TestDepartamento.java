package fp.grados.tipos.test;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestDepartamento {

	public static void main(String[] args) {
		Departamento d1 = Grados.createDepartamento("LSI");
		System.out.println("==SE CREA EL DEPARTAMENTO d1: "+d1);
		System.out.println("====NOMBRE: "+d1.getNombre());
		System.out.println("====ASIGNATURAS: "+d1.getAsignaturas());
		System.out.println("====PROFESORES: "+d1.getProfesores());
		System.out.println("");
		
		Profesor p1 = Grados.createProfesor("53344582S", "Juan", "Solo", LocalDate.of(1991, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, d1);
		System.out.println("==SE CREA PROFESOR p1: " +p1);
		System.out.println("====DEPARTAMENTO DE p1: " +p1.getDepartamento());
		d1.nuevoProfesor(p1);
		System.out.println("==SE AÑADE p1 a d1");
		System.out.println("====NOMBRE: "+d1.getNombre());
		System.out.println("====ASIGNATURAS: "+d1.getAsignaturas());
		System.out.println("====PROFESORES: "+d1.getProfesores());
		System.out.println("====DEPARTAMENTO DE p1: " +p1.getDepartamento());
		Asignatura a1 = Grados.createAsignatura("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		System.out.println("==SE CREA ASIGNATURA a1: " +a1);
		d1.nuevaAsignatura(a1);
		System.out.println("==SE AÑADE a1 a d1");
		System.out.println("====NOMBRE: "+d1.getNombre());
		System.out.println("====ASIGNATURAS: "+d1.getAsignaturas());
		System.out.println("====PROFESORES: "+d1.getProfesores());
		Asignatura a2 = Grados.createAsignatura("Matematica Discreta","2030005",6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d1);
		System.out.println("==SE CREA ASIGNATURA a2: " +a2);
		d1.nuevaAsignatura(a2);
		System.out.println("==SE AÑADE a2 a d1");
		System.out.println("====NOMBRE: "+d1.getNombre());
		System.out.println("====ASIGNATURAS: "+d1.getAsignaturas());
		System.out.println("====PROFESORES: "+d1.getProfesores());
		System.out.println("");
		System.out.println("==SE ELIMINA ASIGNATURA a1");
		d1.eliminaAsignatura(a1);
		System.out.println("====NOMBRE: "+d1.getNombre());
		System.out.println("====ASIGNATURAS: "+d1.getAsignaturas());
		System.out.println("====PROFESORES: "+d1.getProfesores());
		System.out.println("");
		p1.nuevaTutoria(LocalTime.of(12, 30), 120, DayOfWeek.MONDAY);
		p1.nuevaTutoria(LocalTime.of(13, 30), 120, DayOfWeek.THURSDAY);
		System.out.println("==SE AÑADEN 2 NUEVAS TUTORIAS A p1: "+p1.getTutorias());
		d1.borraTutorias();
		System.out.println("==SE BORRAN TODAS LAS TUTORIAS: "+p1.getTutorias());
	
	}

}
