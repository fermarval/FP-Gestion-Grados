package fp.grados.tipos.test;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestExpediente {
	public static void main(String[] args) {
		Expediente e1 = new ExpedienteImpl2();
		System.out.println(e1);
		System.out.println(e1.getNotas());
		System.out.println(e1.getNotaMedia());
		System.out.println();
		
		Departamento d1 = Grados.createDepartamento("LSI");
		Asignatura a1 = Grados.createAsignatura("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura a2 = Grados.createAsignatura("Estadistica","2050003",6.0, TipoAsignatura.ANUAL, 1,d1);
		Nota n1 = new NotaImpl(a1, 2014, Convocatoria.PRIMERA, 5.);
		Nota n2 = new NotaImpl(a1, 2014, Convocatoria.PRIMERA, 6.5);
		Nota n3 = new NotaImpl(a1, 2013, Convocatoria.TERCERA, 4.);
		Nota n4 = new NotaImpl(a2, 2013, Convocatoria.PRIMERA, 7.);
		e1.nuevaNota(n1);
		e1.nuevaNota(n2);
		e1.nuevaNota(n3);
		e1.nuevaNota(n4);
		
		System.out.println(e1);
		System.out.println(e1.getNotas());
		System.out.println(e1.getNotaMedia());
		System.out.println();
		System.out.println(e1.getNotasOrdenadasPorAsignatura());
		System.out.println(e1.getMejorNota());
	}
}
