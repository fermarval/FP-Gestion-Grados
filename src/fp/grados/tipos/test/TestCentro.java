package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestCentro {

	public static void main(String[] args) {
		
		Centro c1 = Grados.createCentro("ETSII", "Reina Mercedes", 4, 1);
		System.out.println("== SE CREA EL CENTRO c1: "+c1);
		System.out.println("==== NOMBRE: "+c1.getNombre());
		System.out.println("==== DIRECCION: "+c1.getDireccion());
		System.out.println("==== PLANTAS: "+c1.getNumeroPlantas());
		System.out.println("==== SOTANOS: "+c1.getNumeroSotanos());
		System.out.println("==== ESPACIOS: "+c1.getEspacios());
		System.out.println("==== CONTEO: "+Arrays.toString(c1.getConteosEspacios()));
		System.out.println("==== DESPACHOS: "+c1.getDespachos());
		System.out.println("==== PROFESORES: "+c1.getProfesores());
		System.out.println();
		System.out.println("== SE CREAN 2 ESPACIOS e1 e2");
		Espacio e1 = Grados.createEspacio(TipoEspacio.EXAMEN, "Aula examen 1", 95, 3);
		Espacio e2 = Grados.createEspacio(TipoEspacio.LABORATORIO, "Aula Laboratorio", 13, 2);
		System.out.println("== AÑADE LOS 2 ESPACIOS e1 e2");
		c1.nuevoEspacio(e1);
		c1.nuevoEspacio(e2);
		System.out.println("==== NOMBRE: "+c1.getNombre());
		System.out.println("==== DIRECCION: "+c1.getDireccion());
		System.out.println("==== PLANTAS: "+c1.getNumeroPlantas());
		System.out.println("==== SOTANOS: "+c1.getNumeroSotanos());
		System.out.println("==== ESPACIOS: "+c1.getEspacios());
		System.out.println("==== CONTEO: "+Arrays.toString(c1.getConteosEspacios()));
		System.out.println("==== DESPACHOS: "+c1.getDespachos());
		System.out.println("==== PROFESORES: "+c1.getProfesores());
		System.out.println();
		System.out.println("== SE CREA Y AÑADE NUEVO ESPACIO TIPO EXAMEN");
		Espacio e3 = Grados.createEspacio(TipoEspacio.EXAMEN, "Aula examen 2", 90, 3);
		c1.nuevoEspacio(e3);
		System.out.println("==== NOMBRE: "+c1.getNombre());
		System.out.println("==== DIRECCION: "+c1.getDireccion());
		System.out.println("==== PLANTAS: "+c1.getNumeroPlantas());
		System.out.println("==== SOTANOS: "+c1.getNumeroSotanos());
		System.out.println("==== ESPACIOS: "+c1.getEspacios());
		System.out.println("==== CONTEO: "+Arrays.toString(c1.getConteosEspacios()));
		System.out.println("==== DESPACHOS: "+c1.getDespachos());
		System.out.println("==== PROFESORES: "+c1.getProfesores());
		System.out.println();
		Departamento d1 = Grados.createDepartamento("LSI");
		System.out.println("== SE CREA EL DEPARTAMENTO d1: "+d1);
		
		Set<Profesor> sp = new HashSet<Profesor>();
		Profesor p1 = Grados.createProfesor("53344582S", "Juan", "Solo", LocalDate.of(1984, 12, 28), "juan.solo@gmail.com", Categoria.TITULAR, d1);
		Profesor p2 = Grados.createProfesor("53344582S", "Manuel", "Asecas", LocalDate.of(1972, 11, 27), "manu.asecas@gmail.com", Categoria.COLABORADOR, d1);
		sp.add(p1); sp.add(p2);
		
		Despacho dcho1 = new DespachoImpl("Despacho 1", 4, 1);
		Despacho dcho2 = new DespachoImpl("Despacho 2", 4, 1, p1);
		Despacho dcho3 = new DespachoImpl("Despacho 3", 4, 1, sp);
		c1.nuevoEspacio(dcho1);
		c1.nuevoEspacio(dcho2);
		c1.nuevoEspacio(dcho3);
		System.out.println("== SE CREA Y AÑADE 3 NUEVOS DESPACHOS");
		System.out.println("==== NOMBRE: "+c1.getNombre());
		System.out.println("==== DIRECCION: "+c1.getDireccion());
		System.out.println("==== PLANTAS: "+c1.getNumeroPlantas());
		System.out.println("==== SOTANOS: "+c1.getNumeroSotanos());
		System.out.println("==== ESPACIOS: "+c1.getEspacios());
		System.out.println("==== CONTEO: "+Arrays.toString(c1.getConteosEspacios()));
		System.out.println("==== DESPACHOS: "+c1.getDespachos());
		System.out.println("==== PROFESORES: "+c1.getProfesores());
		System.out.println();
		Departamento d2 = Grados.createDepartamento("DTE");
		System.out.println("== SE CREA NUEVO DEPARTAMENTO Y SE CAMBIA A p2 DE DEPARTAMENTO: "+d2);
		p2.setDepartamento(d2);
		System.out.println("==== DEPARTAMENTO DE p2: "+p2.getDepartamento());
		System.out.println("==== PROFESORES DE d2: "+d2.getProfesores());
		System.out.println("==== PROFESORES DE d2*: "+c1.getProfesores(d2));
		System.out.println("==== DESPACHOS DE d2: "+c1.getDespachos(d2));
		System.out.println("==== DESPACHOS DE d1: "+c1.getDespachos(d1));
		System.out.println();
		
		System.out.println("== ESPACIO DE MAYOR CAPACIDAD: "+c1.getEspacioMayorCapacidad());
		System.out.println("== ESPACIOS: "+c1.getEspacios());
		System.out.println("== ESPACIOS ORDENADO POR CAPACIDAD: "+c1.getEspaciosOrdenadosPorCapacidad());
		System.out.println(c1.getDespachosPorProfesor());
		
	}
}
