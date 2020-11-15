package modelo;

public class Arma extends Item {
	public enum TipoArma {
		CORTO(2),
		MEDIO(3),
		LARGO(5);
		
		public int alcance;
		TipoArma(int alcance) {
			this.alcance = alcance;
		}
	}
	/*
	 * Att: Nombre, daño, alcance, tipo y nivel de ruido.
	 * */
	public int ruido, alcance;
	public TipoArma tipo;
	public Arma(String nombre, int potencia, int pRuido, TipoArma tipo) {
		super(nombre, potencia);
		this.ruido = pRuido;
		this.alcance = tipo.alcance;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Arma: " + nombre;
	}
	
	
}
