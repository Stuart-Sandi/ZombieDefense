package modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Habilidades {
	Pistolero(Arrays.asList(
				Habilidad.Pasos,
				Habilidad.DannoExtra,
				Habilidad.Defensa)),
	Sniper(Arrays.asList(
				Habilidad.ProbEvadirAtaques,
				Habilidad.AccionesPorTurno, 
				Habilidad.VidaExtra)),
	Ninja(Arrays.asList(
				Habilidad.Escalar, 
				Habilidad.ProbExperienciaExtra, 
				Habilidad.Pasos)),
	//Zombies
	ZombieListener(Arrays.asList(
				Habilidad.ProbEvadirAtaques,
				Habilidad.DannoExtra)),
	
	ZombieKirstein(Arrays.asList(
				Habilidad.DannoExtra,
				Habilidad.Defensa)),
	
	ZombieChayanne(Arrays.asList(
				Habilidad.ProbEvadirAtaques,
				Habilidad.Defensa));
	
	public java.util.ArrayList<Habilidad> habilidades;
	Habilidades(List<Habilidad> asList) {
		habilidades  = new ArrayList<Habilidad>(asList);
	}
	
	
}
