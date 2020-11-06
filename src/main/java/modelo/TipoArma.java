package modelo;

public enum TipoArma {
	//El numero es la distancia del ruido
	CORTO(2),
	LARGO(5);

	public int ruido;
	TipoArma(int i) {
		this.ruido = i;
	}
}
