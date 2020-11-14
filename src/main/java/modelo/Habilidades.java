package modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Habilidades {
	Pistolero(Arrays.asList(Habilidad.Pasos, Habilidad.DannoExtra, Habilidad.Defensa)),
	Sniper(Arrays.asList(Habilidad.AccionesPorTurno, Habilidad.VidaExtra, Habilidad.ProbEvadirAtaques)),
	Ninja(Arrays.asList(Habilidad.Escalar, Habilidad.ProbExperienciaExtra, Habilidad.Pasos));
	
	public java.util.ArrayList<Habilidad> habilidades;
	Habilidades(List<Habilidad> asList) {
		habilidades  = new ArrayList<Habilidad>(asList);
	}
	
	
}
