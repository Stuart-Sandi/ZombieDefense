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
		this.habilidades = pTipo.habilidades.habilidades;
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
		this.habilidades = Habilidades.Pistolero.habilidades;
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
		this.habilidades = pTipo.habilidades.habilidades;
		this.inventario = new HashMap<Integer, Item>();
	}
	
	public void Mover(Direccion pDireccion) {
		
		Posicion posicionAnterior = posicion.Mover(pDireccion);
		Boolean sePuedeMover = revisarMoviemiento();
		System.out.println(sePuedeMover);
		if(!sePuedeMover) {
			this.posicion = posicionAnterior;
		}
	}

	private Boolean revisarMoviemiento() {
		Casilla[][] tablero  = Aplicacion.getInstance().mapa.tablero;

		if(tablero[posicion.x][posicion.y].elemento.getClass().getSimpleName() == Edificacion.class.getSimpleName()) {
			if( tipo == TipoJugador.Ninja & Habilidad.Escalar.nivel <= this.nivel) {
				return true;
			}
			return false;
		}
		return true;
	}
	
	//ganarXP
}
