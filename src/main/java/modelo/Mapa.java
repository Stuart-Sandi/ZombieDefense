package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Mapa {
	
	public Casilla[][] tablero;
	public Bloque[][] bloques;
	public Personaje[][] personajes;
	public HashMap<Integer, GeneradorZombie> puntoSpawn;
	
	
	public Mapa() {
		super();
		this.tablero = new Casilla[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.bloques = new Bloque[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.personajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		generarTablero();
	}


	//cesped.jpg
	public void generarTablero() {
		Icon imgBloque = Helpers.getImagenResized("cesped", ".jpg", 30, 30);
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
                Bloque bloque = new Bloque(imgBloque);
                Casilla casilla = new Casilla<Icon>(bloque.icon);
                this.tablero[i][j] = casilla;
                this.bloques[i][j] = bloque;

            }
        }
	}
	
	public void generarJugadores() {
		ArrayList<Jugador> lista = new ArrayList<Jugador>();
		TipoJugador[] listTipoJugadores = TipoJugador.values();
		ArrayList<Posicion> lstPosiciones = new ArrayList<Posicion>(){ 
            { 
                add(new Posicion(ValoresDefecto.altoTablero-1, ValoresDefecto.anchoTablero-1)); 
                add(new Posicion(ValoresDefecto.altoTablero-2, ValoresDefecto.anchoTablero-1)); 
                add(new Posicion(ValoresDefecto.altoTablero-3, ValoresDefecto.anchoTablero-1)); 
                add(new Posicion(ValoresDefecto.altoTablero-4, ValoresDefecto.anchoTablero-1)); 
            } 
        };
		for (int i = 0; i < lista.size(); i++) {
			lista.add(new Jugador(listTipoJugadores[i], lstPosiciones.get(i)));
		}
	}
	

}
