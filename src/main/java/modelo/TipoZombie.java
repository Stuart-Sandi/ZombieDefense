package modelo;

public enum TipoZombie {
	//pasos, distanciaVision, vida, cantidadAcciones, nivel;
	ZombieListener(15,8,90,3, 4, Habilidades.ZombieListener),
	ZombieKirstein(10,5,200,3,8, Habilidades.ZombieKirstein),
	ZombieChayanne(10,5,120,3,8, Habilidades.ZombieChayanne);
	public int pasos, distanciaVision, vida, cantidadAcciones, nivel;
	public Habilidades habilidades;
	private TipoZombie(int pasos, int distanciaVision, int vida, int cantidadAcciones, int nivel, Habilidades habilidades) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.cantidadAcciones = cantidadAcciones;
		this.nivel = nivel;
		this.habilidades = habilidades;
	}
}
