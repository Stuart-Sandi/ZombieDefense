package modelo;

public enum Habilidad {
	//nivel y valor
	Pasos("Mas Pasos",1, 10),
	DannoExtra("Daño extra",5, 2),
	Defensa("Mas defensa",10, 2),
	
	ProbEvadirAtaques("Probabilidad de Evadir ataques",1, 60),
	AccionesPorTurno("Mas acciones por turno", 5, 5),
	VidaExtra("Vida extra",10, 40),
	
	Escalar("Saltar muros",1, 0),
	ProbExperienciaExtra("Probabilidad experiencia extra",5, 50);
	
	public int nivel, valor;
	public String nombre;
	Habilidad(String nombre, int i, int j) {
		this.nombre = nombre;
		this.nivel = i;
		this.valor = j;
	}
}
