package modelo;

public enum Habilidad {
	//nivel y valor
	Pasos("Mas Pasos",4, 10),
	DannoExtra("Daño extra",6, 2),
	Defensa("Mas defensa",8, 2),
	
	AccionesPorTurno("Mas acciones por turno", 5, 5),
	VidaExtra("Vida extra",6, 40),
	ProbEvadirAtaques("Probabilidad de Evadir ataques",4, 60),
	
	ProbExperienciaExtra("Probabilidad experiencia extra",5, 50),
	Escalar("Saltar muros",4, 0);
	
	public int nivel, valor;
	public String nombre;
	Habilidad(String nombre, int i, int j) {
		this.nombre = nombre;
		this.nivel = i;
		this.valor = j;
	}
}
