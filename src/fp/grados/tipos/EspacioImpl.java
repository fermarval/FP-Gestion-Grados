package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;


public class EspacioImpl implements Espacio {

	private TipoEspacio tipo;
	private String nombre;
	private Integer capacidad, planta;
	
	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
		this.tipo = tipo;
		this.nombre = nombre;
		checkCapacidad(capacidad);
		this.capacidad = capacidad;
		this.planta = planta;
	}
	
	//B10
	public EspacioImpl(String s){
		String[] trozos = s.split(",");
		if(trozos.length!=4){
			throw new IllegalArgumentException("EspacioImpl.EspacioImpl: Cadena no válida");
		}
		this.nombre = trozos[0].trim();
		this.planta = new Integer(trozos[1].trim());
		this.capacidad = new Integer(trozos[2].trim());
		checkCapacidad(capacidad);
		this.tipo = TipoEspacio.valueOf(trozos[3].trim());
	}
	
	private void checkCapacidad(Integer cap){
		if(cap<1){
			throw new ExcepcionEspacioNoValido("EspacioImpl.checkCapacidad: La capacidad no puede ser inferior a 1.");
		}
	}

	public TipoEspacio getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Integer getPlanta() {
		return planta;
	}

	public void setTipo(TipoEspacio tip) {
		this.tipo = tip;
	}

	public void setNombre(String nom) {
		this.nombre = nom;
	}

	public void setCapacidad(Integer cap) {
		checkCapacidad(cap);
		this.capacidad = cap;
	}
	
	//Criterio de igualdad: nombre y planta
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Espacio){
			Espacio es = (Espacio) o;
			res = this.getNombre().equals(es.getNombre()) &&
					this.getPlanta().equals(es.getPlanta());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getNombre().hashCode()+this.getPlanta().hashCode()*31;
	}
	
	//Orden natural: planta > nombre
	public int compareTo(Espacio es){
		int res = this.getPlanta().compareTo(es.getPlanta());
		if(res==0){
			res = this.getNombre().compareTo(es.getNombre());
		}
		return res;
	}
	
	//A3.10 (planta 3)
	public String toString(){
		return this.getNombre()+" (planta "+this.getPlanta()+")";
	}

	
}
