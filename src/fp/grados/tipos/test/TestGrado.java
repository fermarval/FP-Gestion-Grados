package fp.grados.tipos.test;

import java.util.*;

import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestGrado {
	
public static void main(String[] args) {
	
	//Departamento
	Departamento d1 = Grados.createDepartamento("Departamento 1");
	Departamento d2 = Grados.createDepartamento("Departamento 2");
	
	//Asignaturas
	Asignatura a1 = Grados.createAsignatura("Asignatura 1","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
	Asignatura a2 = Grados.createAsignatura("Asignatura 2","2050002",6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d2);
	Asignatura a3 = Grados.createAsignatura("Asignatura 3","2050003",6.0, TipoAsignatura.SEGUNDO_CUATRIMESTRE, 2,d2);
	Asignatura a4 = Grados.createAsignatura("Asignatura 4","2050004",12.0, TipoAsignatura.ANUAL, 1,d2);
	Asignatura a5 = Grados.createAsignatura("Asignatura 5","2050005",12.0, TipoAsignatura.ANUAL, 2,d2);
	
	//Set Asignaturas Obligatorias
	Set<Asignatura> setOb = new HashSet<Asignatura>();
	setOb.add(a1);setOb.add(a2);setOb.add(a3);
	
	//Set Asignaturas Obligatorias
	Set<Asignatura> setOp = new HashSet<Asignatura>();
	setOp.add(a4);setOp.add(a5);
	
	//Construir un grado
	Grado g1 = Grados.createGrado("Grado 1", setOb, setOp, 24.);
	
	System.out.println("Grado 1 construido: "+g1);
	System.out.println();
	//Metodos get y set
	System.out.println("getNombre: "+g1.getNombre());
	System.out.println("getAsignaturasObligatoras: "+g1.getAsignaturasObligatorias());
	System.out.println("getAsignaturasOptativas: "+g1.getAsignaturasOptativas());
	System.out.println("getNumeroMinimoCreditosOptativas: "+g1.getNumeroMinimoCreditosOptativas());
	System.out.println("getNumeroTotalCreditos: "+g1.getNumeroTotalCreditos());
	System.out.println("getDepartamentos: "+g1.getDepartamentos());
	System.out.println("getProfesores: "+g1.getProfesores());
	System.out.println("getAsignaturas(1º curso): "+g1.getAsignaturas(1));
	System.out.println("getAsignatura(2050002): "+g1.getAsignatura("2050002"));
	System.out.println();
	System.out.println(g1.getDepartamentosOrdenadosPorAsignaturas());
	System.out.println(g1.getCreditosPorAsignatura());
}
}
