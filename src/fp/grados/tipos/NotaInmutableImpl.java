package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

public final class NotaInmutableImpl implements Nota{

	private final Asignatura asignatura;
	private final Integer cursoAcademico;
	private final Convocatoria convocatoria;
	private final Double valor;
	private final Boolean mencionHonor;
	
	public NotaInmutableImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionHonor){
		this.asignatura = copiarAsignatura(asignatura);
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		checkValor(valor);
		this.valor = valor;
		checkMencionHonor(valor, mencionHonor);
		this.mencionHonor = mencionHonor;
	}
	
	public NotaInmutableImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor){
		this.asignatura = copiarAsignatura(asignatura);
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		checkValor(valor);
		this.valor = valor;
		this.mencionHonor = false;
	}
	
	public NotaInmutableImpl(String s){
		String[] trozos = s.split(";");
		if(trozos.length!=5){
			throw new IllegalArgumentException("NotaInmutableImpl.NotaInmutableImpl: Cadena no válida");
		}
		this.asignatura = new AsignaturaImpl(trozos[0].trim());
		this.cursoAcademico = new Integer(trozos[1].trim());
		this.convocatoria = Convocatoria.valueOf(trozos[2].trim());
		Double valor = new Double(trozos[3].trim());
		checkValor(valor);
		this.valor = valor;
		Boolean mencionHonor = new Boolean(trozos[4].trim());
		checkMencionHonor(valor, mencionHonor);
		this.mencionHonor = mencionHonor;
		
	}
	
	private void checkValor(Double val){
		if(val<0 || val>10){
			throw new ExcepcionNotaNoValida("NotaInmutableImpl.checkValor: El valor debe comprender entre 0 y 10");
		}
	}
	
	private void checkMencionHonor(Double val, Boolean men){
		if(val<9 && men){
			throw new ExcepcionNotaNoValida("NotaInmutableImpl.checkMencionHonor: La nota no alcanza para ser concedida una mención de honor");
		}
	}
	
	private Asignatura copiarAsignatura(Asignatura a){
		return new AsignaturaImpl(a.getNombre(), a.getCodigo(), a.getCreditos(), a.getTipo(), a.getCurso(), a.getDepartamento());
	}
	
	public Asignatura getAsignatura() {
		return copiarAsignatura(asignatura);
	}

	public Integer getCursoAcademico() {
		return cursoAcademico;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public Double getValor() {
		return valor;
	}

	public Boolean getMencionHonor() {
		return mencionHonor;
	}

	public Calificacion getCalificacion() {
		Calificacion res = Calificacion.SUSPENSO;
		if(getValor()>=9 && getMencionHonor()){
			res = Calificacion.MATRICULA_DE_HONOR;
		}else if(getValor()>=9){
			res = Calificacion.SOBRESALIENTE;
		}else if(getValor()>=7 && getValor()<9){
			res = Calificacion.NOTABLE;
		}else if(getValor()>=5 && getValor()<7){
			res = Calificacion.APROBADO;
		}
		return res;
	}
	
	//Criterio de igualdad: curso, asignatura y convocatoria
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Nota){
			Nota no = (Nota) o;
			res = this.getCursoAcademico().equals(no.getCursoAcademico()) &&
					this.getAsignatura().equals(no.getAsignatura()) &&
					this.getConvocatoria().equals(no.getConvocatoria());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getCursoAcademico().hashCode()+this.getAsignatura().hashCode()*31+this.getConvocatoria().hashCode()*31*31;
	}
	
	//Orden natural: curso > asignatura > convocatoria
	public int compareTo(Nota no){
		int res = this.getCursoAcademico().compareTo(no.getCursoAcademico());
		if(res==0){
			res = this.getAsignatura().compareTo(no.getAsignatura());
			if(res==0){
				res = this.getConvocatoria().compareTo(no.getConvocatoria());
			}
		}
		return res;
	}
	
	//“(0000230) Fundamentos de Programación, 2014-15, PRIMERA, 7.5, NOTABLE
	
	private String strCursoAcademico(Integer curso){
		Integer pA = this.getCursoAcademico()+1;
		return this.getCursoAcademico()+"-"+pA.toString().substring(2);
	}
	
	public String toString(){
		return this.getAsignatura()+", "+this.strCursoAcademico(this.getCursoAcademico())+", "+this.getConvocatoria()+", "+this.getValor()+", "+this.getCalificacion();
	}
}
