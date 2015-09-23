package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestProfesor2 {
public static void main(String[] args) {
	
	Departamento d1 = Grados.createDepartamento("Departamento 1");
	//Departamento d2 = new DepartamentoImpl("Departamento 2");
	Asignatura a1 = Grados.createAsignatura("Asignatura 1","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
	Asignatura a2 = Grados.createAsignatura("Asignatura 2","2050002",18.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d1);
	
	Profesor p1 = Grados.createProfesor("53344582S", "Nombre", "Asecas", LocalDate.of(1981, 12, 28), "profe@email.com", Categoria.INTERINO, d1);
	
	mostrarProfesor(p1);
	
	p1.imparteAsignatura(a1, 5.);
	
	p1.imparteAsignatura(a2, 18.);
	mostrarProfesor(p1);

	p1.eliminaAsignatura(a2);

	mostrarProfesor(p1);
	

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
