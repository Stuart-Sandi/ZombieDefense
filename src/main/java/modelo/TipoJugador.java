package modelo;

public enum TipoJugador{
	//pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	Pistolero(8,      // pasos
			  5,     //distanciaVision
			  100,  //vida
			  3,   //cantidadAcciones
			  10, //experiencia
			  Habilidades.Pistolero,
			  ArmasPrecargadas.GLOCK.arma),
	
	Sniper(5,      //pasos
		   10,     //distanciaVision
		   80,   //vida
		   3,   //cantidadAcciones
		   20, //experiencia
		   Habilidades.Sniper,
		   ArmasPrecargadas.DRAGUNOV.arma),
	
	Ninja(17,     //pasos
		  17,    //distanciaVision
		  75,   //vida
		  3,   //cantidadAcciones
		  15, //experiencia
		  Habilidades.Ninja,
		  ArmasPrecargadas.CUCHILLO.arma);

	
	
	public int pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	public Arma armaInicial;
	public Habilidades habilidades;
	TipoJugador(int pasos, int distanciaVision, int vida, int cantidadAcciones, int experiencia, Habilidades hab, Arma arma) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.cantidadAcciones = cantidadAcciones;
		this.experiencia = experiencia;
		this.habilidades = hab;
		this.armaInicial = arma;
	}
}
