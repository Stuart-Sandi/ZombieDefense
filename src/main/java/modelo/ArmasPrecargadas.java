package modelo;

public enum ArmasPrecargadas{
	GLOCK(new Arma("Glock", 5, 3, Arma.TipoArma.MEDIO)),
	DRAGUNOV(new Arma("Dragunov", 5, 3, Arma.TipoArma.LARGO)),
	CUCHILLO(new Arma("Cuchillo", 5, 3, Arma.TipoArma.CORTO));
	
	Arma arma;
	
	ArmasPrecargadas(Arma arma) {
		this.arma = arma;
	}
	
}
