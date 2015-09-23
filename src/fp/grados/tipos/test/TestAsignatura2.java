package fp.grados.tipos.test;



import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestAsignatura2 {

	public static void main(String[] args) {
		
		Departamento d1 = new DepartamentoImpl("LSI");
		System.out.println("== SE CREA DEPARTAMENTO d1: "+d1);
		Asignatura a1 = Grados.createAsignatura("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		System.out.println("== SE CREA ASIGNATURA a1: "+a1);
		System.out.println("== CUYO DEPARTAMENTO ES: "+a1.getDepartamento());
		System.out.println("== LAS ASIGNATURAS DEL DEPARTAMENTO DE a1: "+a1.getDepartamento().getAsignaturas());
		System.out.println("== LAS ASIGNATURAS DE d1 SON: "+d1.getAsignaturas());
//		d1.nuevaAsignatura(a1);
//		System.out.println("Asignaturas del departamento d1:"+d1.getAsignaturas());
//		System.out.println("--Se Crea departamento d2--");
		Departamento d2 = Grados.createDepartamento("DTE");
		System.out.println("== SE CREA DEPARTAMENTO d2: "+d2);
//		System.out.println("Asignaturas del departamento d2:"+d2.getAsignaturas());
//		System.out.println("--Se cambia departamento a d2--");
		System.out.println("== SE CAMBIA EL DEPARTAMENTO DE a1 por d2: "+d2);
		a1.setDepartamento(d2);
		System.out.println("== EL DEPARTAMENTO DE a1 AHORA ES: "+a1.getDepartamento());
		System.out.println("== LAS ASIGNATURAS DEL DEPARTAMENTO DE a1 AHORA SON: "+a1.getDepartamento().getAsignaturas());
		System.out.println("== ASIGNATURAS DE d1 AHORA SON: "+d1.getAsignaturas());
		System.out.println("== ASIGNATURAS DE d2 AHORA SON: "+d2.getAsignaturas());
		
		//a1.setDepartamento(d1);
		//System.out.println("Asignaturas de d1:"+d1.getAsignaturas());
		//System.out.println("Departamento de a1:"+a1.getDepartamento());
		//System.out.println("Asignaturas del departamento de a1:"+a1.getDepartamento().getAsignaturas());
		
	}
}
