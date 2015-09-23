package fp.grados.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona {

	private String dni, nombre, apellidos, email;
	private LocalDate fechaNacimiento;
	
	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email){
		checkDNI(dni);
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		checkEmail(email);
		this.email = email;
	}
	
	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento){
		checkDNI(dni);
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = "";
	}
	
	//B10
	public PersonaImpl(String s){
		String[] trozos = s.split(",");
		if(trozos.length!=5){
			throw new IllegalArgumentException("PersonaImpl.PersonaImpl: Cadena no válida");
		}
		this.dni = trozos[0].trim();
		checkDNI(dni);
		this.nombre = trozos[1].trim();
		this.apellidos = trozos[2].trim();
		this.fechaNacimiento = LocalDate.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("d/M/y"));
		this.email = trozos[4].trim();
		checkEmail(email);
		
	}
	
	private void checkDNI(String dni){
		if(dni.length()!=9){ //Longitud
			throw new ExcepcionPersonaNoValida("PersonaImpl.checkDNI: El DNI "+dni+" no es correcto.");
		}else{
			for(int i=0;i<=7;i++){
				if(!Character.isDigit(dni.charAt(i))){ //Todos digitos en la parte numérica
					throw new ExcepcionPersonaNoValida("PersonaImpl.checkDNI: El DNI "+dni+" no es correcto.");
				}
			}
			if(!(Character.isAlphabetic(dni.charAt(8)))){ //Ultimo carácter letra
				throw new ExcepcionPersonaNoValida("PersonaImpl.checkDNI: El DNI "+dni+" no es correcto.");
			}else{
				String let = "TRWAGMYFPDXBNJZSQVHLCKET";
				Integer dig = Integer.parseInt(dni.substring(0, 8)); //Parte numérica del DNI
				Integer mod = dig % 23;
				if(let.charAt(mod) != dni.charAt(8)){ //Letra DNI correcta
					throw new ExcepcionPersonaNoValida("PersonaImpl.checkDNI: El DNI "+dni+" no es correcto.");
				}
			}
		}
	}
	
	private void checkEmail(String ema){
		if(!ema.isEmpty() && !ema.contains("@")) //@ en el email
			throw new ExcepcionPersonaNoValida("PersonaImpl.checkEmail: El email "+email+" no contiene @.");
	}

	public String getDNI() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public Integer getEdad() {
		LocalDate hoy = LocalDate.now();
		LocalDate nac = getFechaNacimiento();
		Integer aux = 0; //aux = -1 si aún no ha cumplido años
		if(hoy.getMonthValue() <= nac.getMonthValue()){
			if(hoy.getMonthValue() == nac.getMonthValue()){
				if(hoy.getDayOfMonth() >= nac.getDayOfMonth()){
					aux = 0;
				}else{
					aux = -1;
				}
			}else{
				aux = -1;
			}
		}
		return (hoy.getYear()-nac.getYear())+aux;
	}

	public void setDNI(String dni) {
		checkDNI(dni);
		this.dni = dni;
	}

	public void setNombre(String nom) {
		this.nombre = nom;
	}

	public void setApellidos(String ape) {
		this.apellidos = ape;
	}

	public void setFechaNacimiento(LocalDate fec) {
		this.fechaNacimiento = fec;
	}

	public void setEmail(String ema) {
		checkEmail(ema);
		this.email = ema;
	}
	
	//Criterio de igualdad: dni, nombre y apellidos
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Persona){
			Persona pe = (Persona) o;
			res = this.getDNI().equals(pe.getDNI()) &&
					this.getNombre().equals(pe.getNombre()) &&
					this.getApellidos().equals(pe.getApellidos());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getDNI().hashCode()+this.getNombre().hashCode()*31+getApellidos().hashCode()*31*31;
	}
	
	//Orden natural: apellidos > nombre > dni
	public int compareTo(Persona pe){
		int res = this.getApellidos().compareTo(pe.getApellidos());
		if(res==0){
			res = this.getNombre().compareTo(pe.getNombre());
			if(res==0){
				res = this.getDNI().compareTo(pe.getDNI());
			}
		}
		return res;
	}
	
	//28864657W – García Vaquero, Pascual – 15/09/1998
	
	public String toString(){
		Integer d = this.getFechaNacimiento().getDayOfMonth();
		String sd = d.toString();
		if(sd.length()==1){sd="0"+sd;}
		return this.getDNI()+" - "+this.getApellidos()+", "+this.getNombre()+" - "+sd+"/"+this.getFechaNacimiento().getMonthValue()+"/"+
				this.getFechaNacimiento().getYear();
	}
	
}
