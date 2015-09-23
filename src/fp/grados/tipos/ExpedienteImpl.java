package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import fp.grados.excepciones.*;

public class ExpedienteImpl implements Expediente{

	private List<Nota> notas;
	
	public ExpedienteImpl(){
		this.notas = new ArrayList<Nota>();
	}

	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	public Double getNotaMedia() {
		Double sum = 0.;
		Integer count = 0;
		Double res = 0.;
		for(Nota x: this.getNotas()){
			if(x.getValor() >= 5.){
				sum += x.getValor();
				count++;
			}
		}
		if(count!=0){
			res = sum / count;
		}else{
			res = 0.;
		}
		return res;
	}
	
	public void nuevaNota(Nota n){
		checkConvocatorias(n);
		if(this.getNotas().contains(n)){
			this.notas.remove(n);
			this.notas.add(n);
		}else{
			this.notas.add(n);
		}
		
	}
	
	private void checkConvocatorias(Nota n) {
		Integer count = 0;
		for(Nota x: this.getNotas()){ //Recorre todas notas
			if(x.getAsignatura().equals(n.getAsignatura()) && x.getCursoAcademico().equals(n.getCursoAcademico())){
				count++; //Cuenta aquellas que sean la misma asignatura y curso
			}
		}
		if(count==2){ //Si hay 2 notas iguales no puede haber una tercera
			throw new ExcepcionExpedienteOperacionNoPermitida("ExpedienteImpl.checkConvocatorias: Un expediente no puede contener notas para más de dos convocatorias de una misma asignatura y curso.");
		}
	}

	//Criterio de igualdad: notas
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Expediente){
			Expediente ex = (Expediente) o;
			res = this.getNotas().equals(ex.getNotas());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getNotas().hashCode();
	}
	public String toString(){
		return String.join(",",this.getNotas().toString());
	}
	
	//B12
	public List<Nota> getNotasOrdenadasPorAsignatura(){
		Comparator<Nota> cmp1 = Comparator.comparing(Nota::getAsignatura);
		Comparator<Nota> cmp2 = cmp1.thenComparing(Comparator.naturalOrder());
		List<Nota> res = new ArrayList<Nota>(getNotas());
		Collections.sort(res,cmp2);
		return res;
	}
	
	//B12
	public Nota getMejorNota(){
		Comparator<Nota> cmp1 = Comparator.comparing(Nota::getMencionHonor);
		Comparator<Nota> cmp2 = Comparator.comparing(Nota::getValor);
		Comparator<Nota> cmp3 = Comparator.comparing(Nota::getConvocatoria).reversed();
		Comparator<Nota> cmp4 = Comparator.comparing(Nota::getCursoAcademico).reversed();
		Comparator<Nota> cmp = cmp1.thenComparing(cmp2).thenComparing(cmp3).thenComparing(cmp4);
		
		return getNotas().stream()
				.max(cmp)
				.orElseThrow(NoSuchElementException::new);
	}
}
