package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestDespacho {

	public static void main(String[] args) {
		
		Set<Profesor> sp = new HashSet<Profesor>();
		Profesor p1 = Grados.createProfesor("53344582S", "Juan", "Solo", LocalDate.of(1984, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, null);
		Profesor p2 = Grados.createProfesor("53344582S", "Manuel", "Asecas", LocalDate.of(1972, 11, 27), "manu.asecas@gmail.com", Categoria.COLABORADOR, null);
		//Profesor p3 = new ProfesorImpl("53344582S", "AManuel", "Asecas", LocalDate.of(1972, 11, 27), "manu.asecas@gmail.com", Categoria.COLABORADOR);
		sp.add(p1); sp.add(p2);
		
		Despacho d1 = new DespachoImpl("Despacho 1", 2, 2, sp);
		System.out.println("Test constructor 1: "+d1);
		System.out.println("Profesores d1: "+d1.getProfesores());
		System.out.println();
		
		Despacho d2 = new DespachoImpl("Despacho 2", 2, 2, p1);
		System.out.println("Test constructor 2:"+d2);
		System.out.println("Profesores d2: "+d2.getProfesores());
		System.out.println();
		
		Despacho d3 = new DespachoImpl("Despacho 3", 2, 2);
		System.out.println("Test constructor 3:"+d3);
		System.out.println("Profesores d3: "+d3.getProfesores());
		System.out.println();
		
		
		System.out.println("Tipo d1: "+d1.getTipo());
		Set<Profesor> sp2 = new HashSet<Profesor>();
		sp2.add(p1); sp2.add(p2);//sp2.add(p3);
		d2.setProfesores(sp2);
	}
}
