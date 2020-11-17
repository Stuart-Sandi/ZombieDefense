package modelo;

public enum TipoZombie {
	//pasos, distanciaVision, vida, daño, nivel;
	ZombieListener(15,8,50,20, 4, Habilidades.ZombieListener),
	ZombieKirstein(5,5,120,55,8, Habilidades.ZombieKirstein),
	ZombieChayanne(10,5,80,35,8, Habilidades.ZombieChayanne);
	public int pasos, distanciaVision, vida, dano, nivel;
	public Habilidades habilidades;
	private TipoZombie(int pasos, int distanciaVision, int vida, int dano, int nivel, Habilidades habilidades) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.dano = dano;
		this.nivel = nivel;
		this.habilidades = habilidades;
	}
}
