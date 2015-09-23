package fp.grados.tipos.test;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestEspacio {

	public static void main(String[] args) {

		
		Espacio e1 = Grados.createEspacio(TipoEspacio.EXAMEN, "Aula examen", 90, 2);
		mostrarEspacio(e1);
		Espacio e2 = Grados.createEspacio(TipoEspacio.LABORATORIO, "Aula Laboratorio", 13, 2);
		mostrarEspacio(e2);
		e2.setCapacidad(50);
		mostrarEspacio(e2);
		System.out.println(e1.equals(e2));
		System.out.println(e1.compareTo(e2));
		
	}
	

	private static void mostrarEspacio(Espacio e) {
		System.out.println("Espacio --> <" + e + ">");
		System.out.println("\tTipo: <" + e.getTipo() + ">");
		System.out.println("\tNombre: <" + e.getNombre() + ">");
		System.out.println("\tCapacidad: <" + e.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + e.getPlanta()+">");
		System.out.println();
	}
}	
