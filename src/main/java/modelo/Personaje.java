package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Personaje {
	private ArrayList<Habilidad> habilidades;
	public HashMap<Integer, Item> inventario;
	public Posicion posicion;
	public TipoPersonaje tipo;
	public int pasos, distanciaVision, vida, cantidadAcciones, nivel, experiencia;
	public Boolean vivo;
			   
	public Personaje(TipoPersonaje pTipo) {
		super();
		this.tipo = pTipo;
		this.posicion = new Posicion();
		
	}
	
	public Personaje(TipoPersonaje pTipo, int x, int y) {
		super();
		this.tipo = pTipo;
		this.posicion = new Posicion(x, y);
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
	}
	
	public void Atacar(Posicion pPosicion, Personaje pPersonaje) {
		//Todo hacer el metodo de atacar
	}
	
	public void recibirAtaque(int valor) {
		this.vida -= valor;
		if (vida < 0) {
			this.vivo = false;
		}
	}
}
