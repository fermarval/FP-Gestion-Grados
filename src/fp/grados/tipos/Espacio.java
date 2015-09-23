package fp.grados.tipos;

public interface Espacio extends Comparable<Espacio>{

	TipoEspacio getTipo();
	String getNombre();
	Integer getCapacidad();
	Integer getPlanta();
	
	void setTipo(TipoEspacio tip);
	void setNombre(String nom);
	void setCapacidad(Integer cap);
}
