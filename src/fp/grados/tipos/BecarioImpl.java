package fp.grados.tipos;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;

public class BecarioImpl extends AlumnoImpl implements Becario{
	
	private Beca beca;
	private LocalDate fechaComienzo;
	
	public BecarioImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Beca beca, LocalDate fechaComienzo) {
		super(dni, nombre, apellidos, fechaNacimiento, email);

		this.beca = beca;
		checkFechaComienzo(fechaComienzo);
		this.fechaComienzo = fechaComienzo;
	}
	
	public BecarioImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, String codigo, 
			Double cuantiaTotal, Integer duracion, TipoBeca tipo, LocalDate fechaComienzo){
		super(dni, nombre, apellidos, fechaNacimiento, email);
		
		this.beca = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		checkFechaComienzo(fechaComienzo);
		this.fechaComienzo = fechaComienzo;
	}
	
	private void checkFechaComienzo(LocalDate fec){
		if(!fec.isAfter(LocalDate.now())){
			throw new ExcepcionBecarioNoValido("BecarioImpl.checkFechaComienzo: La fecha de comienzo debe ser posterior a la fecha actual.");
		}
	}

	public Beca getBeca() {
		return beca;
	}

	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}

	public LocalDate getFechaFin() {
		return getFechaComienzo().plusMonths(this.getBeca().getDuracion());
	}

	public void setFechaComienzo(LocalDate fec) {
		checkFechaComienzo(fec);
		this.fechaComienzo = fec;
	}
	
	public void setEmail(String email){
		throw new UnsupportedOperationException("BecarioImpl.setEmail: El email de un becario no se puede modificar.");
	}
	
	//“(1º) 28864657W – García Vaquero, Pascual – 15/09/1998 [ABB2024, movilidad]”
	
	public String toString(){
		return super.toString()+" "+this.getBeca().toString();
	}
	
	
}
