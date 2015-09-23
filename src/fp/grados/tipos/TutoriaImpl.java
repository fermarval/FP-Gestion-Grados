package fp.grados.tipos;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria{

	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin){
		checkDia(diaSemana);
		this.diaSemana = diaSemana;
		checkDuracion((int) horaComienzo.until(horaFin, ChronoUnit.MINUTES));
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion){
		checkDia(diaSemana);
		this.diaSemana = diaSemana;
		this.horaFin = horaComienzo.plusMinutes(duracion);
		checkDuracion((int) horaComienzo.until(horaFin, ChronoUnit.MINUTES));
		this.horaComienzo = horaComienzo;
	}
	
	//B10
	public TutoriaImpl(String s){
		String[] trozos = s.split(",");
		if(trozos.length!=3){
			throw new IllegalArgumentException("TutoriaImpl.TutoriaImpl: Cadena no válida");
		}
		this.horaComienzo = LocalTime.parse(trozos[1].trim());
		this.horaFin = LocalTime.parse(trozos[2].trim());
		checkDuracion((int) horaComienzo.until(horaFin, ChronoUnit.MINUTES));
		this.diaSemana = convierteDia(trozos[0].trim().toUpperCase());
		checkDia(diaSemana);
	}
	
	private DayOfWeek convierteDia(String d) {
		DayOfWeek res = DayOfWeek.SUNDAY;
		if(d.equals("L"))
			res = DayOfWeek.MONDAY;
		else if(d.equals("M"))
			res = DayOfWeek.TUESDAY;
		else if(d.equals("X"))
			res = DayOfWeek.WEDNESDAY;
		else if(d.equals("J"))
			res = DayOfWeek.THURSDAY;
		else if(d.equals("V"))
			res = DayOfWeek.FRIDAY;
		
		return res;
	}

	private void checkDuracion(Integer dur) {
		if(dur<15){
			throw new ExcepcionTutoriaNoValida("TutoriaImpl.chekDuracion: La duración mínima es de 15 minutos.");
		}
	}

	private void checkDia(DayOfWeek dia){
		if(dia.equals(DayOfWeek.SATURDAY) || dia.equals(DayOfWeek.SUNDAY)){
			throw new ExcepcionTutoriaNoValida("TutoriaImpl.checkDia: Sólo puede darse de lunes a viernes.");
		}
	}
	

	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}

	public LocalTime getHoraComienzo() {
		return horaComienzo;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public Integer getDuracion() {
		return (int) ChronoUnit.MINUTES.between(getHoraComienzo(), getHoraFin());
	}
	
	//Criterio de igualdad: día y hora
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Tutoria){
			Tutoria tu = (Tutoria) o;
			res = this.getDiaSemana().equals(tu.getDiaSemana()) &&
					this.getHoraComienzo().equals(tu.getHoraComienzo());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getDiaSemana().hashCode()+this.getHoraComienzo().hashCode()*31;
	}
	
	//Orden natural: día > hora
	public int compareTo(Tutoria tu){
		int res = this.getDiaSemana().compareTo(tu.getDiaSemana());
		if(res==0){
			res = this.getHoraComienzo().compareTo(tu.getHoraComienzo());
		}
		return res;
	}
	
	//X 10:30-12:30
	public String toString(){
		return getDiaSemana().getDisplayName(TextStyle.NARROW, Locale.getDefault())+" "+getHoraComienzo()+"-"+getHoraFin();
	}
	
	
}
