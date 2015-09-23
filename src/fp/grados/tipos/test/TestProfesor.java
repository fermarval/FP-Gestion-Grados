package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestProfesor {

	public static void main(String[] args) {
		
		Departamento d1 = Grados.createDepartamento("LSI");
		System.out.println("== SE CREA DEPARTAMENTO d1: "+d1);
		System.out.println("");
		
		//PRUEBA CONSTRUCTOR PROFESORIMPL
		Profesor p1 = Grados.createProfesor("53344582S", "Juan", "Solo", LocalDate.of(1991, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, d1);
		mostrarProfesor(p1);
		System.out.println("");
		//Profesor p2 = new ProfesorImpl("53344582S", "Juan", "Solo", LocalDate.of(1991, 11, 27), "juan.solo@gmail.com", Categoria.TITULAR, null);
		
		//PRUEBA SET FECHANACIMIENTO
		System.out.println("==EDAD DE p1: " +p1.getEdad());
		LocalDate f1 = LocalDate.of(1981, 12, 28);
		System.out.println("==FECHA DE NACIMIENTO f1: " +f1);
		p1.setFechaNacimiento(f1);
		System.out.println("==SETNACIMIENTO, NUEVA FECHA DE p1: " +p1.getFechaNacimiento()+" Y EDAD: "+p1.getEdad());
		System.out.println("");
//		String e1 = "juan.s@gmail.com";
//		System.out.println("==EMAIL e1: " +e1);
//		p1.setEmail(e1);
//		System.out.println("==SETEMAIL, NUEVO EMAIL DE p1: " +p1.getEmail());
//		
		//PRUEBA SET CATEGORIA
		Categoria c1 = Categoria.AYUDANTE;
		System.out.println("==CATEGORIA c1: " +c1);
		p1.setCategoria(c1);
		System.out.println("==SETCATEGORIA, NUEVA CATEGORIA DE p1: " +p1.getCategoria());
		System.out.println("");
		
		//PRUEBA NUEVATUTORIA Y BORRATUTORIA
//		System.out.println(p2);
//		System.out.println(p2.getEdad());
		System.out.println("==TUTORIAS DE p1: "+p1.getTutorias());
//		Tutoria t1 = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 30));
//		System.out.println("==SE CREA TUTORIA t1: " +t1);
		p1.nuevaTutoria(LocalTime.of(11, 30), 15, DayOfWeek.MONDAY);
		System.out.println("==SE AÑADE UNA TUTORIA A p1: "+p1.getTutorias());
		p1.borraTutoria(LocalTime.of(11, 30), DayOfWeek.MONDAY);
		System.out.println("==SE BORRA LA ANTERIOR TUTORIA EN p1: "+p1.getTutorias());
		p1.nuevaTutoria(LocalTime.of(12, 30), 120, DayOfWeek.MONDAY);
		p1.nuevaTutoria(LocalTime.of(13, 30), 120, DayOfWeek.THURSDAY);
		System.out.println("==SE AÑADEN 2 NUEVAS TUTORIAS A p1: "+p1.getTutorias());
		p1.borraTutorias();
		System.out.println("==SE BORRAN TODAS LA STUTORIAS EN p1: "+p1.getTutorias());
		System.out.println("");
		
		System.out.println("== EL DEPARTAMENTO DE p1 ES: "+p1.getDepartamento());
		System.out.println("== LOS PROFESORES DEL DEPARTAMENTO DE a1: "+p1.getDepartamento().getProfesores());
		System.out.println("== LOS PROFESORES DE d1 SON: "+d1.getProfesores());

		Departamento d2 = Grados.createDepartamento("DTE");
		System.out.println("== SE CREA DEPARTAMENTO d2: "+d2);

		//PRUEBA SET DEPARTAMENTO
		System.out.println("== SETDEPARTAMENTO, SE CAMBIA EL DEPARTAMENTO DE p1 por d2: "+d2);
		p1.setDepartamento(d2);
		System.out.println("== EL DEPARTAMENTO DE p1 AHORA ES: "+p1.getDepartamento());
		System.out.println("== LOS PROFESORES DEL DEPARTAMENTO DE p1 AHORA SON: "+p1.getDepartamento().getProfesores());
		System.out.println("== LOS PROFESORES DE d1 AHORA SON: "+d1.getProfesores());
		System.out.println("== LOS PROFESORES DE d2 AHORA SON: "+d2.getProfesores());
		System.out.println("");
		
		//PRUEBA IMPARTEASIGNATURA Y ELIMINAASIGNATURA
		Asignatura a1 = Grados.createAsignatura("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d2);
		Asignatura a2 = Grados.createAsignatura("Matematica Discreta","2030005",13.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d2);
		System.out.println("== SE CREA ASIGNATURA a1: "+a1);
		System.out.println("== SE CREA ASIGNATURA a2: "+a2);
		mostrarProfesor(p1);
		System.out.println("====DEDICACION a1: " +p1.dedicacionAsignatura(a1));
		System.out.println("====DEDICACION a2: " +p1.dedicacionAsignatura(a2));
		p1.imparteAsignatura(a1, 12.0);
		System.out.println("==SE AÑADE LA ASIGNATURA a1 A p1");
		mostrarProfesor(p1);
		System.out.println("====DEDICACION a1: " +p1.dedicacionAsignatura(a1));
		System.out.println("====DEDICACION a2: " +p1.dedicacionAsignatura(a2));
		p1.imparteAsignatura(a2, 6.0);
		System.out.println("==SE AÑADE LA ASIGNATURA a2 A p1");
		mostrarProfesor(p1);
		System.out.println("====DEDICACION a1: " +p1.dedicacionAsignatura(a1));
		System.out.println("====DEDICACION a2: " +p1.dedicacionAsignatura(a2));
		p1.imparteAsignatura(a2, 12.0);
		System.out.println("==SE AÑADE LA ASIGNATURA a1 (CON OTRA DEDICACIÓN) A p1");
		mostrarProfesor(p1);
		System.out.println("====DEDICACION a1: " +p1.dedicacionAsignatura(a1));
		System.out.println("====DEDICACION a2: " +p1.dedicacionAsignatura(a2));
		p1.eliminaAsignatura(a1);
		System.out.println("==SE ELIMINA LA ASIGNATURA a1");
		mostrarProfesor(p1);
		System.out.println("====DEDICACION a1: " +p1.dedicacionAsignatura(a1));
		System.out.println("====DEDICACION a2: " +p1.dedicacionAsignatura(a2));
		System.out.println("==SE ELIMINA LA ASIGNATURA a2");
		mostrarProfesor(p1);
		System.out.println("====DEDICACION a1: " +p1.dedicacionAsignatura(a1));
		System.out.println("====DEDICACION a2: " +p1.dedicacionAsignatura(a2));
		
		
	}
	
	private static void mostrarProfesor(Profesor p){
		System.out.println("Profesor --> <" + p + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ p.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
		System.out.println("\tCategoría:  <" + p.getCategoria() + ">");
		System.out.println("\tDepartamento:  <" + p.getDepartamento() + ">");
		System.out.println("\tAsignaturas:  <" + p.getAsignaturas() + ">");
		System.out.println("\tCréditos:  <" + p.getCreditos() + ">");
		System.out.println("\tDedicación total:  <" + p.getDedicacionTotal() + ">");
	}
}
