package fp.grados.tipos;

import java.util.OptionalDouble;


public class ExpedienteImpl2 extends ExpedienteImpl implements Expediente{
	
	public ExpedienteImpl2(){
		super();
	}

	//B13
	public Double getNotaMedia() {
		OptionalDouble opt = getNotas().stream()
				.filter(n->n.getValor()>=5.)
				.mapToDouble(n->n.getValor())
				.average();
		if(!opt.isPresent())
			return 0.;
		else
			return opt.getAsDouble();
	}
}
