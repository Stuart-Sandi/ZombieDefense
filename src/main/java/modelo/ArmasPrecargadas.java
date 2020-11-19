package modelo;

public enum ArmasPrecargadas{
	GLOCK(new Arma("Glock: Alcance 3", 25, 3, Arma.TipoArma.MEDIO)),
	DRAGUNOV(new Arma("Dragunov: Alcance 5", 60, 3, Arma.TipoArma.LARGO)),
	CUCHILLO(new Arma("Cuchillo: Alcance 2", 15, 3, Arma.TipoArma.CORTO));
	
	Arma arma;
	
	ArmasPrecargadas(Arma arma) {
		this.arma = arma;
	}
	
}
