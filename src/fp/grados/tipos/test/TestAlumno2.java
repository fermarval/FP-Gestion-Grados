package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestAlumno2 {
public static void main(String[] args) {
	Departamento d1 = Grados.createDepartamento("Departamento 1");
	Asignatura as1 = Grados.createAsignatura("Asignatura 1", "2050011", 6., TipoAsignatura.PRIMER_CUATRIMESTRE, 1, d1);
	Asignatura as2 = Grados.createAsignatura("Asignatura 2", "2050012", 6., TipoAsignatura.PRIMER_CUATRIMESTRE, 3, d1);
	Asignatura as3 = Grados.createAsignatura("Asignatura 3", "2050013", 6., TipoAsignatura.PRIMER_CUATRIMESTRE, 2, d1);
	Alumno al1 = Grados.createAlumno("12345678Z", "Juaan", "Nadie Nadie",
			LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	al1.matriculaAsignatura(as1);
	al1.matriculaAsignatura(as2);
	al1.matriculaAsignatura(as3);
	al1.evaluaAlumno(as1, 1, Convocatoria.PRIMERA, 5.);
	al1.evaluaAlumno(as2, 3, Convocatoria.PRIMERA, 7.);
	al1.evaluaAlumno(as3, 2, Convocatoria.PRIMERA, 3.);
	
	System.out.println(al1);
	System.out.println(al1.getAsignaturas());
	System.out.println(al1.getAsignaturasOrdenadasPorCurso());
	System.out.println(al1.getCurso());
	System.out.println(al1.getCalificacionPorAsignatura());
}

}
