package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;



public class AlumnoImpl2 extends AlumnoImpl implements Alumno{

	
	public AlumnoImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		
	}
	
	public AlumnoImpl2(String s){
		super(s);
	}


	//B12	
	public Integer getCurso() {
		Optional<Asignatura> opt = getAsignaturas().stream().max(Comparator.comparing(Asignatura::getCurso));
		if(!opt.isPresent())
			return 0;
		else
			return opt.get().getCurso();
	}
	
	//B13
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura(){
		SortedMap<Asignatura, Calificacion> res = new TreeMap<Asignatura, Calificacion>();
		getExpediente().getNotas().stream()
			.forEach(n->actualizaCalificacionPorAsignatura(res,n));
		return res;
	}
	
	private void actualizaCalificacionPorAsignatura(SortedMap<Asignatura, Calificacion> m, Nota n) {
		Calificacion c = m.get(n.getAsignatura());
		
		if(c == null || c.compareTo(n.getCalificacion())<0){
			m.put(n.getAsignatura(), n.getCalificacion());	
		}
	}

}
