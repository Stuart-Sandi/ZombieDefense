package modelo;

public enum Habilidad {
	Pasos(4),
	DannoExtra(6),
	Defensa(8),
	
	AccionesPorTurno(5),
	VidaExtra(6),
	ProbEvadirAtaques(4),
	
	ProbExperienciaExtra(5),
	Escalar(4);
	
	public int nivel;
	Habilidad(int i) {
		this.nivel = i;
	}
}
