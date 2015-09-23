package fp.grados.excepciones;

public class ExcepcionDepartamentoOperacionNoPermitida extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionDepartamentoOperacionNoPermitida(){
		super();
	}
	
	public ExcepcionDepartamentoOperacionNoPermitida(String s){
		super(s);
	}
}
