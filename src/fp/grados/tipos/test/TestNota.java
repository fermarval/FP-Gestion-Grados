package fp.grados.tipos.test;


import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestNota {
	
	public static void main(String[] args) {

		Departamento d1 = Grados.createDepartamento("LSI");
		System.out.println("== SE CREA DEPARTAMENTO d1: "+d1);
		Asignatura a1 = Grados.createAsignatura("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		System.out.println("== SE CREA ASIGNATURA a1: "+a1);
		
		Nota n1 = new NotaImpl(a1, 2014, Convocatoria.PRIMERA, 7.5);
		System.out.println("== SE CREA NOTA n1: "+n1);
		Nota n2 = new NotaImpl(a1, 2014, Convocatoria.SEGUNDA, 9., true);
		System.out.println("== SE CREA NOTA n2: "+n2);
		System.out.println("==== ASIGNATURA: "+n1.getAsignatura());
		System.out.println("==== CALIFICACION: "+n1.getCalificacion());
		System.out.println("==== CONVOCATORIA: "+n1.getConvocatoria());
		System.out.println("==== CURSO: "+n1.getCursoAcademico());
		System.out.println("==== MENCION HONOR: "+n1.getMencionHonor());
		System.out.println("==== VALOR: "+n1.getValor());
	}
}	

