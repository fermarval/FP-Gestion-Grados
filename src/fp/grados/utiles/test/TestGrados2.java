package fp.grados.utiles.test;

import java.time.LocalDate;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestGrados2 {
	public static void main(String[] args) {
		
		Asignatura aa = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,null);
		Asignatura ab = new AsignaturaImpl("Asignatura 2",
				"2050002", 12.0, TipoAsignatura.ANUAL, 1,null);
		Alumno al = new AlumnoImpl("12345678Z", "Juaaan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Grados.createAlumnos("res/alumnos.txt");
		al.matriculaAsignatura(aa);
		al.matriculaAsignatura(ab);
		
		Alumno fa1 = Grados.createAlumno(al);
		
		System.out.println(al);
		System.out.println(al.getAsignaturas());
		System.out.println(al.getExpediente());
		System.out.println(fa1);
		System.out.println(fa1.getAsignaturas());
		System.out.println(fa1.getExpediente());
		
	}
}
