package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public final class BecaInmutableImpl implements BecaInmutable{

	private final String codigo;
	private final Double cuantiaTotal;
	private final Integer duracion;
	private final TipoBeca tipo;
	private static final Double CUANTIA_MINIMA = 1500.0;
	
	public BecaInmutableImpl(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo){
		checkCodigo(codigo);
		this.codigo = codigo;
		checkCuantia(cuantiaTotal);
		this.cuantiaTotal = cuantiaTotal;
		checkDuracion(duracion);
		this.duracion = duracion;
		this.tipo = tipo;
	}
	
	public BecaInmutableImpl(String codigo, TipoBeca tipo){
		checkCodigo(codigo);
		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = CUANTIA_MINIMA;
		this.duracion = 1;
	}
	
	public BecaInmutableImpl(String s){
		String[] trozos = s.split(",");
		if(trozos.length!=4){
			throw new IllegalArgumentException("BecaInmutableImpl.BecaInmutableImpl: Cadena no válida");
		}
		String codAux = trozos[0].trim();
		checkCodigo(codAux);
		this.codigo = codAux;
		Double cuaAux = new Double(trozos[1].trim());
		checkCuantia(cuaAux);
		this.cuantiaTotal = cuaAux;
		Integer durAux = new Integer(trozos[2].trim());
		checkDuracion(durAux);
		this.duracion = durAux;
		this.tipo = TipoBeca.valueOf(trozos[3].trim());
	}
	
	private void checkCodigo(String cod) {
		if(cod.length()!=7)
			throw new ExcepcionBecaNoValida("BecaImpl.checkCodigo: El código "+codigo+" no es válido.");
		else{
			for(int i=3;i<=6;i++)
				if(!Character.isDigit(cod.charAt(i))){
					throw new ExcepcionBecaNoValida("BecaImpl.checkCodigo: El código "+codigo+" no es válido.");
				}
			if(!(Character.isAlphabetic(cod.charAt(0)) && (Character.isAlphabetic(cod.charAt(1))) 
					&& (Character.isAlphabetic(cod.charAt(2)))))
				throw new ExcepcionBecaNoValida("BecaImpl.checkCodigo: El código "+codigo+" no es válido.");
		}
	}
	
	private void checkDuracion(Integer dur){
		if(dur<1){
			throw new ExcepcionBecaNoValida("BecaInmutableImpl.checkDuracion: La duración no puede ser inferior a 1.");
		}
	}
	
	private void checkCuantia(Double cua){
		if(cua<CUANTIA_MINIMA){
			throw new ExcepcionBecaNoValida("BecaInmutableImpl.checkCuantia: La cuantia total es inferior a "+CUANTIA_MINIMA+" euros.");
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	public Double getCuantiaMensual() {
		return this.getCuantiaTotal()/this.getDuracion();
	}
	
	//Criterio de igualdad: código y tipo
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof BecaInmutable){
			BecaInmutable be = (BecaInmutable) o;
			res = this.getCodigo().equals(be.getCodigo()) &&
					this.getTipo().equals(be.getTipo());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getCodigo().hashCode()+this.getTipo().hashCode()*31;
	}
	
	//Orden natural: código > tipo
	public int compareTo(BecaInmutable be){
		int res = this.getCodigo().compareTo(be.getCodigo());
		if(res==0){
			res = this.getTipo().compareTo(be.getTipo());
		}
		return res;
	}
	
	public String toString(){
		return "["+this.getCodigo()+", "+this.getTipo()+"]";
				
	}

}
