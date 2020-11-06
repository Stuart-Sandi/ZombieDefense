package modelo;

public class Arma extends Item {
	public TipoArma alcance;
	public int ruido;
	public Arma(String nombre, int valor, TipoArma alcance) {
		super(nombre, valor);
		this.alcance = alcance;
	}
	
}
