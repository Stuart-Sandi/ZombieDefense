package modelo;

public enum TipoZombie {
	//pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	ZombieListener(15,15,90,3,20),
	ZombieKirstein(10,10,200,3,50),
	ZombieChayanne(10,10,120,3,30);
	public int pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	private TipoZombie(int pasos, int distanciaVision, int vida, int cantidadAcciones, int experiencia) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.cantidadAcciones = cantidadAcciones;
		this.experiencia = experiencia;
		
	}
}
