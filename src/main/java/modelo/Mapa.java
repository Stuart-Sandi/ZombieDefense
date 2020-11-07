package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Mapa {
	
	public Casilla[][] tablero;
	
	public Mapa() {
		super();
		this.tablero = new Casilla[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
	}
	

}
