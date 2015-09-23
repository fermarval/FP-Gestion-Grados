package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;
import fp.grados.utiles.Grados;

// Este c�digo se les da como material adjunto al bolet�n T3,
// a excepci�n de los tests del constructor 3 y de setEmail()
public class TestPersona {

	public static void main(String[] args) {
		
		List<Persona>	personas	=	Grados.leeFichero("res/personas.txt",
				s->new	PersonaImpl(s));
		
		System.out.println(personas);

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();
		testConstructor1Excepcional4();

		testSetDNINormal();
		testSetDNIExcepcional1();
		testSetDNIExcepcional2();
		testSetDNIExcepcional3();

		testConstructor2Normal();
		testConstructor2Excepcional1();
		testConstructor2Excepcional2();
		testConstructor2Excepcional3();
		testConstructor2Excepcional4();
		
		testSetEmailNormal();
		testSetEmailExcepcional1();
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor con dni sin letra");
		testConstructor1("123456789", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional2() {
		System.out
				.println("==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor1("1234567X", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional3() {
		System.out
				.println("==================================Probando el primer constructor con letra en dni que no se corresponde a los d�gitos");
		testConstructor1("12345678X", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional4() {
		System.out
				.println("==================================Probando el primer constructor con email sin arroba");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadiegmail.com");
	}	
	
	private static void testSetDNINormal(){
		System.out
		.println("==================================Probando setDNI");
			Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
			testSetDNI(p, "12345677J");	
	}
	
	private static void testSetDNIExcepcional1(){
		System.out
		.println("==================================Probando setDNI con dni sin letra");
		
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "123456779");	
	}

	private static void testSetDNIExcepcional2(){
		System.out
		.println("==================================Probando setDNI con dni de longitud menor de la esperada");
		
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677");	
	}
	

	private static void testSetDNIExcepcional3(){
		System.out
		.println("==================================Probando setDNI con letra en dni que no se corresponde a los d�gitos");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677X");	
	}
	
	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el segundo constructor");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional1() {
		System.out
				.println("==================================Probando el segundo constructor con dni sin letra");
		testConstructor2("123456789", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional2() {
		System.out
				.println("==================================Probando el segundo constructor con dni de longitud menor de la esperada");
		testConstructor2("1234567X", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional3() {
		System.out
				.println("==================================Probando el segundo constructor con letra en dni que no se corresponde a los d�gitos");
		testConstructor2("12345678X", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional4() {
		System.out
				.println("==================================Probando el segundo constructor con email sin arroba");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}
	
	private static void testSetEmailNormal(){
		System.out
		.println("==================================Probando setEmail");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "j.nadie@gmail.com");
	}
	
	private static void testSetEmailExcepcional1(){
		System.out
		.println("==================================Probando setEmail con email sin arroba");
		
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "j.nadiegmail.com");	
	}
		
	private static void testConstructor1(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {

		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, 
					fechaNacimiento, email);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testConstructor2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento) {

		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, 
					fechaNacimiento);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada. El constructor no funciona correctamente");
		}

	}
	
	private static void testSetDNI(Persona p, String nuevoDNI) {

		try {
			System.out.println("El dni antes de la operaci�n es: "+  p.getDNI());
			System.out.println("El nuevo dni es: "+  nuevoDNI);
			p.setDNI(nuevoDNI);
			System.out.println("El dni despu�s de la operaci�n es: "+  p.getDNI());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada. setDNI no funciona correctamente");
		}
	}
	
	private static void testSetEmail(Persona p, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operaci�n es: "+  p.getEmail());
			System.out.println("El nuevo email es: "+  nuevoEmail);
			p.setEmail(nuevoEmail);
			System.out.println("El email despu�s de la operaci�n es: "+  p.getEmail());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada. setEmail no funciona correctamente");
		}
	}


	private static void mostrarPersona(Persona p) {
		System.out.println("Persona --> <" + p + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ p.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
	}

}