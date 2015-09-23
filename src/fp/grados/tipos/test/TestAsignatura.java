package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;
import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

// Este c�digo se les da como material adjunto al bolet�n T3
public class TestAsignatura {

	public static void main(String[] args) {
		testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		testConstructorExcepcion5();
		testConstructorExcepcion6();
		testConstructorExcepcion7();		
	}
	
	/******************************** CASOS DE PRUEBA **************************/

	private static void testConstructorNormal() {
		System.out
				.println("==================================Probando el constructor");
		testConstructor("Fundamentos de Programaci�n","2050001",12.0, TipoAsignatura.ANUAL, 1,null);
	}

	private static void testConstructorExcepcion1() {
		System.out
		.println("==================================Probando el constructor, c�digo de asignatura m�s largo");
		testConstructor("Fundamentos de Programaci�n","20500010",12.0, TipoAsignatura.ANUAL, 1,null);
	}
	
	private static void testConstructorExcepcion2() {
		System.out
		.println("==================================Probando el constructor, c�digo de asignatura m�s corto");
		testConstructor("Fundamentos de Programaci�n","205000",12.0, TipoAsignatura.ANUAL, 1,null);
	}
	
	private static void testConstructorExcepcion3() {
		System.out
				.println("==================================Probando el constructor, c�digo de asignatura no num�rico");
		testConstructor("Fundamentos de Programaci�n","2A50001",12.0, TipoAsignatura.ANUAL, 1,null);
	}
	
	private static void testConstructorExcepcion4() {
		System.out
				.println("==================================Probando el constructor, cr�ditos incorrectos (0.0)");
		testConstructor("Fundamentos de Programaci�n","2050001",0.0, TipoAsignatura.ANUAL, 1,null);
	}
		
	private static void testConstructorExcepcion5() {
		System.out
				.println("==================================Probando el constructor, cr�ditos incorrectos (-1.0)");
		testConstructor("Fundamentos de Programaci�n","2050001",-1.0, TipoAsignatura.ANUAL, 1,null);
	}
	
	
	private static void testConstructorExcepcion6() {
		System.out
				.println("==================================Probando el constructor, curso menor de 1");
		testConstructor("Fundamentos de Programaci�n","2050001",12.0, TipoAsignatura.ANUAL, -2,null);
	}
	
	private static void testConstructorExcepcion7() {
		System.out
				.println("==================================Probando el constructor, curso mayor de 4");
		testConstructor("Fundamentos de Programaci�n","2050001",12.0, TipoAsignatura.ANUAL, 5,null);
	}
	
	/******************************** METODOS AUXILIARES **************************/
	
	private static void testConstructor(String nombre, String codigo, Double creditos,
			TipoAsignatura tipo, Integer curso, Departamento departamento) {
		try {
			Asignatura a = Grados.createAsignatura(nombre, codigo, creditos, tipo, curso, departamento);
			mostrarAsignatura(a);
		} catch (ExcepcionAsignaturaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionAsignaturaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** ���Se ha capturado una EXCEPCI�N INESPERADA!!!");
		}
	}


	private static void mostrarAsignatura(Asignatura a) {		
		System.out.println("Assignatura --> <" + a + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tC�digo: <" + a.getCodigo() + ">");
		System.out.println("\tCr�ditos: <" + a.getCreditos() + ">");
		System.out.println("\tTipo: <" + a.getTipo() + ">");
		System.out.println("\tCurso: <" + a.getCurso() + ">");
	}

}