package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Aplicacion {
	
	public Bloque[][] bloques;
	public Personaje[][] personajes;
	public HashMap<Integer, GeneradorZombie> puntoSpawn;
	public HashMap<String, Icon> imagenes;
	public Mapa mapa;
	
	public Aplicacion() {
		super();
		this.mapa = new Mapa();
		this.bloques = new Bloque[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.personajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		generarTablero();
		generarJugadores();
	}

	//cesped.jpg
	public void generarTablero() {
		Icon imgBloque = Helpers.getImagenResized("cesped", ".jpg", 30, 30);
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
                Bloque bloque = new Bloque(imgBloque);
                Casilla casilla = new Casilla<Icon>(bloque.icon);
                this.mapa.tablero[i][j] = casilla;
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
			Jugador jug = new Jugador(listTipoJugadores[i], lstPosiciones.get(i));
			lista.add(jug);
			this.personajes[jug.posicion.x][jug.posicion.y] = jug;
		}
		
	}
}
