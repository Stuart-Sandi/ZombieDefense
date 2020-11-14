package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

public class Jugador extends Personaje{
	
	public TipoJugador tipo;
	public HashMap<Integer, Item> inventario;

	
	public Jugador(TipoJugador pTipo) {
		super();
		this.tipo = pTipo;
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
		this.inventario = new HashMap<Integer, Item>();
	}
	
	public Jugador(TipoJugador pTipo, int x, int y) {
		super(x, y);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
		this.habilidades = new ArrayList<Habilidades>();
		this.inventario = new HashMap<Integer, Item>();
	}

	public Jugador(TipoJugador pTipo, Posicion posicion) {
		super(posicion);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
		this.inventario = new HashMap<Integer, Item>();
	}
	
	public void Mover(Direccion pDireccion) {
		//TODO revisar bloque a mover, por si una habilidad le deja saltar el muro
		Posicion posicionAnterior = posicion.Mover(pDireccion);
		Boolean sePuedeMover = revisarMoviemiento();
		
		if(!sePuedeMover) {
			this.posicion = posicionAnterior;
		}
	}

	private Boolean revisarMoviemiento() {
		Casilla[][] tablero  = Aplicacion.getInstance().mapa.tablero;
		return false;
	}
	
	//ganarXP
}
