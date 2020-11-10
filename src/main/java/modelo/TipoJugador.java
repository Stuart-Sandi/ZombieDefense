package modelo;

public enum TipoJugador{
	//pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	Pistolero(8,8,100,3,10),
	Sniper(5,5,80,3,20),
	Ninja(17,17,75,3,15);
	//Tanque(7,7,150,3,25);
	
	
	public int pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	TipoJugador(int pasos, int distanciaVision, int vida, int cantidadAcciones, int experiencia) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.cantidadAcciones = cantidadAcciones;
		this.experiencia = experiencia;
		
	}
}
