package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

public class Personaje {
	public Posicion posicion;
	public int pasos, distanciaVision, vida, cantidadAcciones, nivel, experiencia;
	public Boolean vivo;
	public Icon imagen;
	public ArrayList<Habilidades> habilidades;
	public Personaje() {
		super();
		this.posicion = new Posicion();
		
	}
	
	public Personaje(int x, int y) {
		super();
		this.posicion = new Posicion(x, y);
	}
	
	public Personaje(Posicion pPosicion) {
		super();
		this.posicion = pPosicion;
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
