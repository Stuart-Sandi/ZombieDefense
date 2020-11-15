package modelo;

public enum Habilidad {
	//nivel y valor
	Pasos(4, 10),
	DannoExtra(6, 2),
	Defensa(8, 2),
	
	AccionesPorTurno(5, 5),
	VidaExtra(6, 40),
	ProbEvadirAtaques(4, 60),
	
	ProbExperienciaExtra(5, 50),
	Escalar(4, 0);
	
	public int nivel, valor;
	Habilidad(int i, int j) {
		this.nivel = i;
		this.valor = j;
	}
}
