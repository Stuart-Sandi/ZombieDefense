package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Personaje {
	private ArrayList<Habilidad> habilidades;
	public HashMap<Integer, Item> inventario;
	
	public TipoPersonaje tipo;
	public int pasos, distanciaVision, vida;
	
			   
	public Personaje(TipoPersonaje pTipo) {
		super();
		this.tipo = pTipo;
	}
	
	
}
