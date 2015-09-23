package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionBecarioNoValido;
import fp.grados.tipos.*;

public class TestTutoria {

	public static void main(String[] args) {

		//testConstructor1Normal();
		//testConstructor1Excepcion1();
		//testConstructor2Excepcion2();
		
		//testConstructor2Normal();
		//testConstructor2Excepcion1();
		//testConstructor2Excepcion2();
		
		//testSetCapacidadNormal();
		//testSetCapacidadExepcion();
		//TODO
		
		LocalTime hc = LocalTime.now();
		LocalTime hf = LocalTime.now().plusMinutes(15);
		long min = ChronoUnit.MINUTES.between(hc, hf);
		System.out.println(hc);
		System.out.println(hf);
		System.out.println(min);
		
		//Tutoria t1 = new TutoriaImpl(DayOfWeek.MONDAY, hc, hf);
		//Tutoria t1 = new TutoriaImpl(null, hf, hf);
	}
	
	@SuppressWarnings("unused")
	private static void testConstructor1(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {

		try {
			Tutoria t = new TutoriaImpl(diaSemana, horaComienzo , horaFin);
			mostrarTutoria(t);
		} catch (ExcepcionBecarioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionBecarioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void mostrarTutoria(Tutoria t) {
		System.out.println("Tutoria --> <" + t + ">");
		System.out.println("\tDia de la semana: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora fin: <" + t.getHoraFin() + ">");
		System.out.println("\tDuracion: <" + t.getDuracion()+">");
	}

}
