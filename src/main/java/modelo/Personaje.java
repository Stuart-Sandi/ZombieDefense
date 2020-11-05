package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Personaje {
	private ArrayList<Habilidad> habilidades;
	public HashMap<Integer, Item> inventario;
	public Posicion posicion;
	public TipoPersonaje tipo;
	public int pasos, distanciaVision, vida, cantidadAcciones;
	
			   
	public Personaje(TipoPersonaje pTipo) {
		super();
		this.tipo = pTipo;
		this.posicion = new Posicion();
	}
	
	public Personaje(TipoPersonaje pTipo, int x, int y) {
		super();
		this.tipo = pTipo;
		this.posicion = new Posicion(x, y);
	}
	
}
