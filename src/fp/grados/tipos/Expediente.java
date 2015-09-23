package fp.grados.tipos;

import java.util.*;

public interface Expediente {

	List<Nota> getNotas();
	Double getNotaMedia();
	
	void nuevaNota(Nota n);
	
	//B12
	List<Nota> getNotasOrdenadasPorAsignatura();
	Nota getMejorNota();
}
