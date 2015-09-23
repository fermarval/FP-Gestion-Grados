package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {

	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;
	
	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo,
			Integer curso, Departamento departamento){
		this.nombre = nombre;
		checkCodigo(codigo);
		this.codigo = codigo;
		checkCreditos(creditos);
		this.creditos = creditos;
		this.tipo = tipo;
		checkCurso(curso);
		this.curso = curso;
		this.setDepartamento(departamento);
	}
	
	//B10
	public AsignaturaImpl(String s){
		String[] trozos = s.split("#");
		if(trozos.length!=5){
			throw new IllegalArgumentException("AsignaturaImpl.AsignaturaImpl: Cadena no válida");
		}
		this.nombre =trozos[0].trim();
		this.codigo = trozos[1].trim();
		checkCodigo(codigo);
		this.creditos = new Double(trozos[2].trim());
		checkCreditos(creditos);
		this.tipo = TipoAsignatura.valueOf(trozos[3].trim());
		this.curso = new Integer(trozos[4].trim());
		checkCurso(curso);
		this.departamento = null;
	}
	
	private void checkCodigo(String cod){
		if(cod.length()!=7){
			throw new ExcepcionAsignaturaNoValida("AsignaturaImpl.checkCodigo: El código "+codigo+" no es válido");
		}else{
			for(int i=0;i<=6;i++){
				if(!Character.isDigit(cod.charAt(i))){ //Todos digitos
					throw new ExcepcionAsignaturaNoValida("AsignaturaImpl.checkCodigo: El código "+codigo+" no es válido");
				}
			}
		}
	}
	
	private void checkCreditos(Double cre){
		if(cre<=0.){
			throw new ExcepcionAsignaturaNoValida("AsignaturaImpl.checkCreditos: El crédito debe ser mayor a 0");
		}
	}
	
	private void checkCurso(Integer cur){
		if(cur<=0 || cur>4){
			throw new ExcepcionAsignaturaNoValida("AsignaturaImpl.checkCurso: Los grados solo constan de 4 años");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getAcronimo() {
		String res = "";
		for(Character c: nombre.toCharArray()){
			if(Character.isUpperCase(c)){
				res=res+c;
			}
		}
		return res;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCreditos() {
		return creditos;
	}

	public TipoAsignatura getTipo() {
		return tipo;
	}

	public Integer getCurso() {
		return curso;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento de){
		
		if(de != this.departamento){ //Si no es el mismo
			Departamento antDe = this.departamento; //Actualizamos el departamento
			this.departamento = de;
			
			if(antDe != null){ //Si ya tenia departamento
				antDe.eliminaAsignatura(this);
			}
			
			if(de != null){
				de.nuevaAsignatura(this);
			}
		}
	}
	
	//Criterio de igualdad: código
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Asignatura){
			Asignatura as = (Asignatura) o;
			res = this.getCodigo().equals(as.getCodigo());
		}
		return res;
	}
	
	public int hashCode(){
		return this.getCodigo().hashCode();
	}
	
	//Orden natural: código
	public int compareTo(Asignatura as){
		int res = this.getCodigo().compareTo(as.getCodigo());
		return res;
	}
	
	public String toString(){
		return "("+this.getCodigo()+") "+this.getNombre();
	}	
}
