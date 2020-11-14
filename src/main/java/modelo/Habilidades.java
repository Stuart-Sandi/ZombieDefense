package modelo;

public enum Habilidades {
	Pasos(4),
	AccionesPorTurno(5),
	DannoExtra(6),
	VidaExtra(7),
	Defensa(8),
	ProbEvadirAtaques(9),
	ProbExperienciaExtra(10),
	Escalar(15);
	
	public int nivel;
	Habilidades(int i) {
		this.nivel = i;
	}
	
	
}
