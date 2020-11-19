package modelo;

public enum Habilidad {
	//nivel y valor
	Pasos("Mas Pasos",1, 10),
	DannoExtra("Daño extra",2, 2),
	Defensa("Mas defensa",3, 2),
	
	ProbEvadirAtaques("Probabilidad de Evadir ataques",1, 40),
	AccionesPorTurno("Mas acciones por turno", 2, 5),
	VidaExtra("Aumentar efectividad consumibles",3, 15),
	
	Escalar("Saltar muros",2, 0),
	ProbExperienciaExtra("Probabilidad experiencia extra",2, 50);
	
	public int nivel, valor;
	public String nombre;
	Habilidad(String nombre, int i, int j) {
		this.nombre = nombre;
		this.nivel = i;
		this.valor = j;
	}
}
