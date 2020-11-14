package modelo;

public enum TipoJugador{
	//pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	Pistolero(8,8,100,3,10, Habilidades.Pistolero),
	Sniper(5,5,80,3,20,     Habilidades.Sniper),
	Ninja(17,17,75,3,15,    Habilidades.Ninja);
	//Tanque(7,7,150,3,25);
	
	
	public int pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	public Habilidades habilidades;
	TipoJugador(int pasos, int distanciaVision, int vida, int cantidadAcciones, int experiencia, Habilidades hab) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.cantidadAcciones = cantidadAcciones;
		this.experiencia = experiencia;
		this.habilidades = hab;
		
	}
}
