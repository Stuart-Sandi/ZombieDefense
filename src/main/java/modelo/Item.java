package modelo;

public class Item implements Cloneable{
	public String nombre;
	public int valor;
	
	public Item(String nombre, int valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	@Override
	protected Item clone() throws CloneNotSupportedException {
		return new Item(this.nombre, this.valor);
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
