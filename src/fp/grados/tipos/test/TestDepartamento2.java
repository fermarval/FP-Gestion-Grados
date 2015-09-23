package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestDepartamento2 {
public static void main(String[] args) {
	Departamento d1 = Grados.createDepartamento("LSI");
	mostrarDepartamento(d1);
	Profesor p1 = Grados.createProfesor("53344582S", "Juan", "Solo", LocalDate.of(1991, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, d1);
	Profesor p2 = Grados.createProfesor("53344582S", "Manuel", "Solo", LocalDate.of(1991, 12, 28), "manuel.solo@gmail.com", Categoria.AYUDANTE, d1);
	d1.nuevoProfesor(p1);
	System.out.println("==SE AÑADE p1 a d1");
	mostrarDepartamento(d1);
	System.out.println("====DEPARTAMENTO DE p1: " +p1.getDepartamento());
	Asignatura a1 = Grados.createAsignatura("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
	d1.nuevaAsignatura(a1);
	p1.imparteAsignatura(a1, 6.0);
	System.out.println("==SE AÑADE a1 a p1");
	System.out.println("==SE AÑADE a1 a d1");
	mostrarDepartamento(d1);
	Asignatura a2 = Grados.createAsignatura("Matematica Discreta","2030005",6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d1);
	System.out.println("==SE CREA ASIGNATURA a2: " +a2);
	d1.nuevaAsignatura(a2);
	System.out.println("==SE AÑADE a2 a d1");
	mostrarDepartamento(d1);
	System.out.println("==SE ELIMINA ASIGNATURA a1");
	d1.eliminaAsignatura(a1);
	mostrarDepartamento(d1);
	p1.nuevaTutoria(LocalTime.of(12, 30), 120, DayOfWeek.MONDAY);
	p1.nuevaTutoria(LocalTime.of(13, 30), 120, DayOfWeek.THURSDAY);
	p2.nuevaTutoria(LocalTime.of(13, 30), 120, DayOfWeek.THURSDAY);
	System.out.println("==SE AÑADEN 2 NUEVAS TUTORIAS A p1: "+p1.getTutorias());
	mostrarDepartamento(d1);
	d1.borraTutorias(Categoria.TITULAR);
	System.out.println("==SE BORRAN TODAS LAS TUTORIAS CON CATEGORIA TITULAR");
	mostrarDepartamento(d1);
	d1.borraTutorias();
	System.out.println("==SE BORRAN TODAS LAS TUTORIAS");
	mostrarDepartamento(d1);
	
	System.out.println("==EXISTE PROFESOR ASIGNADO: " +d1.existeProfesorAsignado(a1));
	System.out.println("==ASIGNATURAS p1: " +p1.getAsignaturas());
	System.out.println("==ASIGNACION p1 a1: "+p1.dedicacionAsignatura(a1));
	d1.eliminaAsignacionProfesorado(a1);
	System.out.println("==ELIMINA ASIGNACION a1");
	System.out.println("==ASIGNACION p1 a1: "+p1.dedicacionAsignatura(a1));
	System.out.println();
	//System.out.println(d1.getProfesorMaximaDedicacionMediaPorAsignatura());
	System.out.println(d1.estanTodasAsignaturasAsignadas());
	d1.eliminaAsignacionProfesorado(a1);
	System.out.println("==EXISTE PROFESOR ASIGNADO TRAS ELIMINAR: " +d1.existeProfesorAsignado(a1));
}

private static void mostrarDepartamento(Departamento d) {
	System.out.println("Departamento --> <" + d + ">");
	System.out.println("\tNombre: <" + d.getNombre() + ">");
	System.out.println("\tAsignaturas: <" + d.getAsignaturas() + ">");
	System.out.println("\tProfesores: <" + d.getProfesores() + ">");
	System.out.println("\tProfesores por Asignatura: <" + d.getProfesoresPorAsignatura() + ">");
	//System.out.println("\tProfesor máxima dedicacion media por asignatura:  <" + d.getProfesorMaximaDedicacionMediaPorAsignatura() + ">");
	System.out.println("\tTutorias por profesor:  <" + d.getTutoriasPorProfesor() + ">\n");
}
}
